# Goal
Requirements
* Create an API to post a message which can be delivered to a recipient via
sms/mobileNo and keep a record of it
* Create an API to retrieve the messages sent to a particular recipient

AWS Services used
===============
* Elastic bean stack
* ELB
* EC2
* S3
* IAM
* Lambda
* SQS
* SNS
* Sprint- Boot, Java 8, Gradle 

Design Approach
---------------
I haven't used CloudFront, API Gateway, VPC as in this scenario I dont need them and other than ELB these are the only tools to face the Internet from the env.

* deployed the Spring-Boot APis on elastic bean stack
* bean stalk uses the EC2 auto scaling group
* these instances are registered with the elastic load balancer
* ELB is the tool which faces the internet, so configure the inbound rules to allow the traffic on 
the port spring app is listening.
* postMessage API , pubishes the received request using SNS with the topic to SQS
* SQS has two queus on subscribed to send the message to the recipient , another queue subscribed to store the message into a db
* one Lambda function will listen to each of these Queues and send the message to the recipient and store in the DB respectively.
* Get Method is straight forward where API , directly returns the results from DB.

This APP is already deployed on Amazon Cloud, Ireland AZs.
 
### How to Test the APP

I have used swagger to document the APIs
goto : http://money-you.eu-west-1.elasticbeanstalk.com/swagger-ui.html#
* given the proper request , message will be delivered to the recipient (Working)
* given recipient id , it will return the messages delivered the particular recipient. (Not working Yet)

Not Done Yet.
------------
* Havent completely written the Unit test because of the limited time.
