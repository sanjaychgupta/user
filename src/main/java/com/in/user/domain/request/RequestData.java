package com.in.user.domain.request;

import javax.xml.bind.annotation.XmlRootElement;
/*
<?xml version="1.0"?>
<requestData>
    <jvmCount>16</jvmCount>
    <maxAttampts>345</maxAttampts>
    <locationXpath>abd/adfd/bdc</locationXpath>
    <requestPayload>sdcad</requestPayload>
</requestData>
 */

@XmlRootElement
public class RequestData {
    public int jvmCount;
    public String locationXpath;
    public int maxAttampts;
    public String requestPayload;
}
