package com.bytrees.cloud.id.generator;

import com.bytrees.cloud.id.generator.creator.DefaultTimeCreator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class IdCreatorTest {
    @Test
    public void create1() {
        Generator generator = Generator.getInstance(new DefaultTimeCreator(), 1);
        long id = generator.genId(0);
        Assert.assertTrue(id > 0);
    }
}
