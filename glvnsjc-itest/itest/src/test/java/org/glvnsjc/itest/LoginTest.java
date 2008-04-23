package org.glvnsjc.itest;

/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * $Id: LoginTest.java,v 1.1 2006/07/06 21:15:26 danttran Exp $
 */

import com.gargoylesoftware.htmlunit.WebClient;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Verify that each of the example apps starts and (at least)
 * displays its default page.
 */
public class LoginTest
    extends TestCase
{

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public LoginTest( String testName )
    {
        super( testName );
    }

    public void setUp()
        throws Exception
    {
        super.setUp();

    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite( LoginTest.class );
        return suite;
    }

    /**
     * Verify that the login page
     */
    public void testLoginPage()
        throws Exception
    {
        WebClient webClient = login();
        webClient.getBrowserVersion();
    }



    

}
