package org.ucll.web4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ucll.web4.entity.ChatPairEntity;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Web4ApplicationTests {

	private final UUID id1 = UUID.fromString("c2f2372d-d040-4cc3-89af-811a0ce777dd");
	private final UUID id2 = UUID.fromString("408e3eab-dcfd-4620-8c6a-e252a19bbe1c");
	private final UUID id3 = UUID.randomUUID();

	private final ChatPairEntity pair1 = new ChatPairEntity(id1,id2);
	private final ChatPairEntity pair2 = new ChatPairEntity(id2,id1);
	private final ChatPairEntity pair3 = new ChatPairEntity(id1,id3);

	@Test
	public void ChatPairInDifferentOrderIsEqual(){
		//logic
		Assert.assertEquals(pair1, pair1);
		Assert.assertEquals(pair2, pair2);

		//same people in the pair so must be equal for inMem db
		Assert.assertEquals(pair1, pair2);
		Assert.assertEquals(pair2, pair1);

		//sanity check
		Assert.assertNotEquals(pair1, pair3);
		Assert.assertNotEquals(pair2, pair3);
		Assert.assertNotEquals(pair3, pair1);
		Assert.assertNotEquals(pair3, pair2);
	}

	@Test
	public void ChatPairInDifferentOrderHasSameHashcode(){
		//logic
		Assert.assertEquals(pair1.hashCode(), pair1.hashCode());
		Assert.assertEquals(pair2.hashCode(), pair2.hashCode());

		//same people in the pair so must be equal for inMem db
		Assert.assertEquals(pair1.hashCode(), pair2.hashCode());
		Assert.assertEquals(pair2.hashCode(), pair1.hashCode());

		//sanity check
		Assert.assertNotEquals(pair1.hashCode(), pair3.hashCode());
		Assert.assertNotEquals(pair2.hashCode(), pair3.hashCode());
		Assert.assertNotEquals(pair3.hashCode(), pair1.hashCode());
		Assert.assertNotEquals(pair3.hashCode(), pair2.hashCode());
	}

}
