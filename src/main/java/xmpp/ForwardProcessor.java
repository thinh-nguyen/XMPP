/*
 * Copyright 2014 Wolfram Rittmeyer.
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
 */
package xmpp;

import java.util.HashMap;
import java.util.Map;

import xmpp.PayloadProcessor;

/**
 * Handles an echo request.
 */
public class ForwardProcessor implements PayloadProcessor{

   // @Override
    public void handleMessage(CcsMessage msg) {
    	 String toRegId = "ev8BysJPHkM:APA91bFuDT5dK_YXBgv4bqt5vvM0RIY1D-bBfuNpfyI19u-y10RzZpi6UW6JS0urrG28tfvz4fWEH0q3-fpbKZYA8dA5T6tMBX0bBOkfLQpBLi9wnAeIFaxo6K9wLS-b5X6Oc4ARmaDG";//args[2];

        PseudoDao dao = PseudoDao.getInstance();
        CcsClient client = CcsClient.getInstance();
        String msgId = dao.getUniqueMessageId();
       
        Map<String, String> payload = new HashMap<String, String>();
        payload.put("message", "Simple sample message");
        String collapseKey = "sample";
        Long timeToLive = 10000L;
        Boolean delayWhileIdle = true;
        Map<String, String> notification = new HashMap<String, String>();
        
        String sender = msg.getPayload().get("sender");
        String tagName = msg.getPayload().get("tag");
        notification.put("body", sender + " is sharing " + tagName);
        notification.put("title", tagName);
        notification.put("sound", "default");
        
        String jsonRequest =  CcsClient.createJsonMessage(
        		toRegId, msgId,  msg.getPayload(), null,
                timeToLive, delayWhileIdle, notification);
        client.send(jsonRequest);
    }

}
