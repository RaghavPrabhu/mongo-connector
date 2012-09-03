/**
 * Copyright (c) MuleSoft, Inc. All rights reserved. http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */


package org.mule.module.mongo.tools;


import org.mule.module.mongo.api.MongoClient;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.Validate;

public class MongoRestore extends AbstractMongoUtility
{
    private MongoClient mongoClient;
    private boolean drop;
    private boolean oplogReplay;
    private boolean applyIncrementals;

    public MongoRestore(MongoClient mongoClient)
    {
        Validate.notNull(mongoClient);
        this.mongoClient = mongoClient;
    }

    public void restore(String inputPath) throws IOException
    {
        Validate.notNull(inputPath);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        MongoRestoreDirectory mongoRestoreDirectory = new MongoRestoreDirectory();
        mongoRestoreDirectory.setInputPath(inputPath);
        mongoRestoreDirectory.setMongoClient(mongoClient);
        mongoRestoreDirectory.setDrop(drop);
        mongoRestoreDirectory.setOplogReplay(oplogReplay);
        mongoRestoreDirectory.setApplyIncrementals(applyIncrementals);
        Future<Void> future = executor.submit(mongoRestoreDirectory);
        propagateException(future);
    }


    public void setDrop(boolean drop)
    {
        this.drop = drop;
    }

    public void setOplogReplay(boolean oplogReplay)
    {
        this.oplogReplay = oplogReplay;
    }

    public void setApplyIncrementals(boolean applyIncrementals)
    {
        this.applyIncrementals = applyIncrementals;
    }

}
