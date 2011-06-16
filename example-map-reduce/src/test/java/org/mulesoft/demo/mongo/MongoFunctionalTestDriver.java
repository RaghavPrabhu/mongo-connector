/**
 * Mule MongoDB Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mulesoft.demo.mongo;

import org.mule.api.MuleEvent;
import org.mule.api.transport.PropertyScope;
import org.mule.construct.SimpleFlowConstruct;
import org.mule.tck.FunctionalTestCase;

public class MongoFunctionalTestDriver extends FunctionalTestCase
{
    @Override
    protected String getConfigResources()
    {
        return "mule-config.xml";
    }

    public void testCreateProductsFlow() throws Exception
    {
        MuleEvent testEvent = getTestEvent("");
        testEvent.getMessage().setProperty("cityIcao", "KMCO", PropertyScope.INBOUND);
        lookupFlowConstruct("AddWeatherObservationAndComputeStats").process(testEvent);
    }

    private SimpleFlowConstruct lookupFlowConstruct(final String name)
    {
        return (SimpleFlowConstruct) muleContext.getRegistry().lookupFlowConstruct(name);
    }

}
