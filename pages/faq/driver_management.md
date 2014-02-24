---
layout: default
title: Driver Management FAQ
permalink: /driver_management_faq.html
heading: Driver Management 
faq: true
---
#Driver Management FAQ

* Q: How can I have a driver object for per thread? <br/>
A: Just set the value of property **thread.based.driver** to **true**. The internal driver provider will automatically return driver on per thread basis.<br/>

* Q: How I can I force the current used driver provided by the in-built driver provider to quit?<br/>
A: In case you want to quit the current driver provided by the in-built driver provider just call the static method _**clearCacheAndQuitDriver**_ present under the class 
_**CacheDriverFactory**_.
<br/>

