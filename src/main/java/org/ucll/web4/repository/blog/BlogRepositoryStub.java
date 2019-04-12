package org.ucll.web4.repository.blog;

import org.springframework.stereotype.Repository;
import org.ucll.web4.entity.BlogEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;



//this class just holds the hardcoded blog entities no need to implement the crud methods. Maybe in a later exercise.
@Repository
public class BlogRepositoryStub implements BlogRepository {

    private final HashMap<UUID,BlogEntity> blogMap;

    public BlogRepositoryStub(){
        this.blogMap = new HashMap<>();

        //create hardcoded topics
        BlogEntity blog1 = new BlogEntity.Builder()
                .withTitle("Hoe was de projectweek?")
                .build();

        BlogEntity blog2 = new BlogEntity.Builder()
                .withTitle("Hoe druk is jouw week met al deze deadlines?")
                .build();

        BlogEntity blog3 = new BlogEntity.Builder()
                .withTitle("Welke topics zou je graag als nieuw vak zien op school?")
                .build();

        BlogEntity blog4 = new BlogEntity.Builder()
                .withTitle("Wat is jouw favoriete vak?")
                .build();

        BlogEntity blog5 = new BlogEntity.Builder()
                .withTitle("Welk vak zou je graag geschrapt zien?")
                .build();

        //add topics
        create(blog1,blog1.getId());
        create(blog2,blog2.getId());
        create(blog3,blog3.getId());
        create(blog4,blog4.getId());
        create(blog5,blog5.getId());
    }

    @Override
    public void create(BlogEntity entity, UUID key) {
        blogMap.put(key,entity);
    }

    @Override
    public BlogEntity get(UUID key) {
        throw new NotImplementedException();
    }

    @Override
    public void update(BlogEntity entity) {
        throw new NotImplementedException();
    }

    @Override
    public List<BlogEntity> getAll() {
        return new ArrayList<>(blogMap.values());
    }

    @Override
    public boolean exists(UUID key) {
        throw new NotImplementedException();
    }
}
