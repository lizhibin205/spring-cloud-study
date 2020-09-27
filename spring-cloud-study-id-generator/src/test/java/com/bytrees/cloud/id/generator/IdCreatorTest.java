package com.bytrees.cloud.id.generator;

import com.bytrees.cloud.id.generator.creator.DefaultTimeCreator;
import com.bytrees.cloud.id.generator.utils.TimeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IdCreatorTest {
    @Test
    public void defaultTimeCreatorTest() {
        long now = TimeUtils.getNowSecond();
        IdCreator creator = new DefaultTimeCreator();
        Generator generator = Generator.getInstance(creator, 1000);
        long id = generator.genId(6);
        Assertions.assertTrue(id > 0);
        IdMeta idMeta = creator.getIdMetaInfo(id);
        Assertions.assertEquals(6, idMeta.getType());
        Assertions.assertEquals(1000, idMeta.getMachineId());
        Assertions.assertEquals(0, idMeta.getSequence());
        Assertions.assertEquals(now, idMeta.getUnixTime());
    }

    @Test
    public void crossSecondCreatIdTest() {
        IdCreator creator = new DefaultTimeCreator();
        Generator generator = Generator.getInstance(creator, 1001);
        long count = 0L;
        long lastId = 0L;
        while (count <= 10000L) {
            long nowId = generator.genId(7);
            Assertions.assertTrue(nowId > lastId);
            lastId = nowId;
            count ++;
        }
    }
}
