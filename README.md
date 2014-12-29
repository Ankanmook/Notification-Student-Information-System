Notification-Student-Information-System
=======================================
Technology: Java, C#, Amazon EC2, ESB, SMTP

- Ankan Mookherjee, Ayemi Musa, Greg Wright

Notification Engine Application for Students Information System based on distributed multitier and service oriented 
architecture. The engine architecture had a Client tier – a lightweight front end running on laptops and mobile devices or SMTP client, a Business tier – a SOAP web service end point implementing mini SIS in addition to a ESB middleware to provide application integration, message routing capability and communication protocol abstraction. 

We present a distributed noti cation system built on loosely coupled services deployed across multiple instances of Amazon 
Elastic Compute Cloud (Amazon EC2) and MuleSoft CloudHub. This application would allow multiple users to subscribe to Class
time table they wish to be reminded of. Once they subscribe, the noti cation engine would send out message alerts at a speci ed time before the beginning of the desired class. Also, it permits subscribers to opt out of their subscription and further noti cations will not be sent out.

DESIGN CONSIDERATIONS
In build this system, a lot of considerations went into the design which in uence the system architecture in several dimensions.
The main decisions concern quality attributes - observable properties that are desired from the user's per- spective. The 
system must be fault tolerant, highly available, and timely as delivery of noti cation must be guaran- teed within stringent
time limits. Given the total enrollment at RIT of approximately 20000, the system is expected to process an increasing number
of transactions concurrently therefore scalability was also an underpinning factor.

ARCHITECTURE
The system design is based on a distributed multi-tiered and service oriented architecture (SOA).
Client Tier | Developed in: C#, WCF
The client tier consists of a light weight web frontend run- ning on laptops and mobile devices to access services in the
business tier. 
Business Tier | Developed in: Java
This tier consists of SOAP web service endpoints which implements the subscription and noti cation services. 
The subscription service receives subscription requests from the client side and places the request on a queue for 
processing, and the noti cation services delivers message alert to the client as events occur. We implemented a system
that mimics SIS (mini SIS) as another web endpoint that pro- vides course cataloging, student enrollment and information
service.

Enterprise Information System Tier (EIS) | Developed in: Java, MySQL
The EIS tier consists of a database system as the main source of data.
The architecture is supported by a lightweight enterprise service bus (ESB) middleware to provide application integration,
message routing capability, publish/subscribe func- tionality, load balancing, and communication protocols ab- straction. 
In addition, the ESB provides failure masking by implementing retries and dead letter queue (DLQ) in the event of service 
unavailability.
