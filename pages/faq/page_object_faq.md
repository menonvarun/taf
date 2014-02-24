---
layout: default
title: Page Object FAQ
permalink: /page_object_faq.html
heading: Enhanced Page Object
faq: true
---
#Enhanced Page Object FAQ

* Q: How can we define a Page Object class in TAF? <br/>
A: Just extend your page class by [PageClass]() class available with TAF. <br/>

* Q: Why do I have to override certain methods while extending the [PageClass]()? <br/>
A: The methods that are required to be overridden while extending the PageClass helps in using the enhanced Page Object Model of TAF.
<br/>
* Q: Can I return a full url while overriding the "to" method? <br/> 
A: Yes you can do so. TAF will automatically identify whether the said url of the page is absolute or relative. If its not absoulute TAF will automatically append the 
base url to the start of the provided page url.
<br/><br/>
