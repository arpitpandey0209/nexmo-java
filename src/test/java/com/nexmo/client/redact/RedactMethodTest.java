/*
 * Copyright (c) 2011-2018 Nexmo Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.nexmo.client.redact;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RedactMethodTest {
    @Test
    public void testConstructParamsWithoutType() throws Exception {
        RedactMethod method = new RedactMethod(null);
        RedactRequest request = new RedactRequest("test-id", RedactRequest.Product.VOICE);

        RequestBuilder builder = method.makeRequest(request);
        HttpEntity entity = builder.getEntity();

        assertEquals(entity.getContentType().getValue(), ContentType.APPLICATION_JSON.toString());
        assertNotNull(entity.getContent());
    }

    @Test
    public void testConstructParamsWithType() throws Exception {
        RedactMethod method = new RedactMethod(null);
        RedactRequest request = new RedactRequest("test-id", RedactRequest.Product.SMS);
        request.setType(RedactRequest.Type.INBOUND);

        RequestBuilder builder = method.makeRequest(request);
        HttpEntity entity = builder.getEntity();

        assertEquals(entity.getContentType().getValue(), ContentType.APPLICATION_JSON.toString());
        assertNotNull(entity.getContent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructParamsWithMissingId() throws Exception {
        RedactMethod method = new RedactMethod(null);
        RedactRequest request = new RedactRequest(null, RedactRequest.Product.SMS);

        method.makeRequest(request);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructParamsWithMissingProduct() throws Exception {
        RedactMethod method = new RedactMethod(null);
        RedactRequest request = new RedactRequest("test-id", null);

        method.makeRequest(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructParamsWithMissingTypeAndSmsProduct() throws Exception {
        RedactMethod method = new RedactMethod(null);
        RedactRequest request = new RedactRequest("test-id", RedactRequest.Product.SMS);

        method.makeRequest(request);
    }
}
