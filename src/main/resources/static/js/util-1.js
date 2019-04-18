class RequestHelper{

    constructor(builder){
        //data
        this.httpMethod = builder.httpMethod;
        this.url = builder.url;
        this.requestParameters = builder.requestParameters;
        this.hasPayload = builder.hasPayload;
        this.expectedHttpResponseCode = builder.expectedHttpResponseCode;
        this.successMessage = builder.successMessage;
        this.errorMessage = builder.errorMessage;
        this.inputNode = builder.inputNode; //feedback goes above input node (input node field in form)
        this.hasErrorHandling = builder.hasErrorHandling;

        //xmlHttpRequest
        this.request = new XMLHttpRequest();
    };

     _createAndAppendFeedbackNode(message,className,inputNode){
        let feedbackNode = document.createElement("p");
        feedbackNode.innerText = message;
        feedbackNode.className = className;

         inputNode.parentNode.insertBefore(feedbackNode, inputNode);

         setTimeout(function () {
             feedbackNode.remove();
         }, 2000);
    };

    sendRequest(successAction){
        this.request.open(this.httpMethod,this.url,true);

        this.request.onreadystatechange = () => {
            if(this.request.readyState === 4){
                //handle feedback first then action
                if(this.request.status === this.expectedHttpResponseCode){
                    if(this.hasErrorHandling){
                        this._createAndAppendFeedbackNode(this.successMessage,"text-success",this.inputNode);
                    }
                    successAction(this.request.responseText);
                }else{
                    if(this.hasErrorHandling){
                        this._createAndAppendFeedbackNode(this.errorMessage,"text-danger",this.inputNode);
                    }
                }
            }
        };

        if(this.hasPayload){
            this.request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            let payload = "";
            for(let i=0;i<this.requestParameters.length;i++){
                if(i === this.requestParameters.length - 1){
                    payload += this.requestParameters[i].toString();
                }else{
                    payload += this.requestParameters[i].toString() + "&";
                }
            }
            this.request.send(payload);

        }else{
            this.request.send(null);
        }
    };

    static get Builder(){
        class Builder{

            withRequestInfo(httpMethod,url,expectedHttpResponseCode){
                this.httpMethod = httpMethod;
                this.url = url;
                this.expectedHttpResponseCode = expectedHttpResponseCode;

                return this;
            }

            withPayload(...requestParameters){
                this.requestParameters = requestParameters;
                this.hasPayload = true;

                return this;
            }

            withErrorHandling(successMessage,errorMessage,inputNode){
                this.successMessage = successMessage;
                this.errorMessage = errorMessage;
                this.inputNode = inputNode;
                this.hasErrorHandling = true;

                return this;
            }

            build(){
                return new RequestHelper(this);
            }
        }

        return Builder;
    };
}

class RequestParameter{

    constructor(key,value){
        this.key = key;
        this.value = encodeURIComponent(value);
    };

    toString(){
        return this.key + "=" + this.value;
    };
}