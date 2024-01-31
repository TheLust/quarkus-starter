package com.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.ArrayEquals;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class XTest {

    @Test
    void mock() {
//        List l1 = Mockito.spy(new ArrayList());
        ArrayList l1 = Mockito.spy(ArrayList.class);

//        Mockito.when(l1.add(any(Integer.class))).thenReturn(true);
        Mockito.when(l1.size()).thenReturn(200);

//        Assertions.assertEquals(200, l1.size());

        l1.add(1);
        l1.add(2);
        l1.add(3);
        Assertions.assertEquals(200, l1.size());
        Assertions.assertEquals(2, l1.get(1));


    }
}
