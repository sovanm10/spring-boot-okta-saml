# spring-boot-okta-saml
##### 1.	Create a new developer account https://developer.okta.com
##### 2.	Sign up. You’ll receive an email to activate your account and change your temporary password. 
![alt text](https://user-images.githubusercontent.com/26097904/127314627-69f58da1-bd44-4fc9-8890-730c71c57122.png)

##### 3.	Make sure you take write down your Okta URL after you’ve signed up. And sing in with user/password
![alt text](https://user-images.githubusercontent.com/26097904/127315959-92c029b0-583b-435b-b7a8-413e2d47cf0c.png)

##### 4.	After login you will get okta home page
![alt text](https://user-images.githubusercontent.com/26097904/127316392-8fd40da5-af20-4c8c-9512-065d6d29366f.png)

##### 5.	Create New App
![alt text](https://user-images.githubusercontent.com/26097904/127316616-4391486b-2358-4a21-818a-929a58244d30.png)

##### 6.	Click on Create App Integration 
![alt text](https://user-images.githubusercontent.com/26097904/127316778-76cb8a04-8dc3-47ae-931d-5c595e46e283.png)

##### 7.	Select SAML 2.0
![alt text](https://user-images.githubusercontent.com/26097904/127317083-fe8cc575-fcb7-416d-ab3f-53b25f2a01b3.png)

##### 8.	Provide application name
![alt text](https://user-images.githubusercontent.com/26097904/127317286-b62a6a2a-92bd-462e-94e4-717f67758071.png)

##### 9.	Enter the following SAML Settings:
      Single Sign-On URL: https://localhost:8080/saml/SSO
      Use this for Recipient URL and Destination URL: YES
      Audience URI: https://localhost:8080/saml/metadata
![alt text](https://user-images.githubusercontent.com/26097904/127317537-b142cc7c-99b5-4735-b537-47ead0be82a7.png)

##### 10.	Click Next
          Select the following two options:
            •	I’m an Okta customer adding an internal app
            •	This is an internal app that we have created
![alt text](https://user-images.githubusercontent.com/26097904/127317813-cd9eac4a-2a44-4586-a70f-c8b7e767e479.png)

##### 11.	Click the Finish button to continue.
![alt text](https://user-images.githubusercontent.com/26097904/127318134-e76612f4-434d-4d0b-9b96-5313399821e8.png)

And copy the Identity Provider metadata link. It will be use in spring boot application configuration.

![alt text](https://user-images.githubusercontent.com/26097904/127318324-b2bd3ee6-ed8b-4757-a06b-64da721c5d56.png)

the Identity Provider metadata link: https://dev-82083850.okta.com/app/exk1cnq4xcw6JcqQo5d7/sso/saml/metadata

##### 12.	The final setup step you’ll need is to assign people to the application. Before that you have to add people
From  left side select  Directory -> People -> Add Person

![alt text](https://user-images.githubusercontent.com/26097904/127318947-b5248c0b-2aba-4468-91a5-3c3db61461d6.png)

##### 13. Assign to user
![alt text](https://user-images.githubusercontent.com/26097904/127319277-d92f782a-aed6-4e84-9820-e6a04ad5bfd2.png)

##### 14. Click on Assign link
![alt text](https://user-images.githubusercontent.com/26097904/127319702-ed415801-f0d8-4873-bafa-33754d05af9a.png)

##### 15. Click on the Assignments tab in your application and the Assign > Assign to People button. You’ll see a list of people with your account in it.
![alt text](https://user-images.githubusercontent.com/26097904/127319952-97fa2e8f-62a9-46e1-947f-3d9515779975.png)

##### 16. Now create a spring boot application. add spring-security-saml-dsl dependency in pom.

      <dependency>
        <groupId>org.springframework.security.extensions</groupId>
        <artifactId>spring-security-saml-dsl</artifactId>
        <version>1.0.0.M3</version>
      </dependency>

##### 17.	Add below property in application yml

      server.port = 8443
      server.ssl.enabled = true
      server.ssl.key-alias = spring
      server.ssl.key-store = classpath:saml/keystore.jks
      server.ssl.key-store-password = secret
      security.saml2.metadata-url = <your metadata url>
      
     - here metadata url: https://dev-82083850.okta.com/app/exk1cnq4xcw6JcqQo5d7/sso/saml/metadata
     
##### 18.	Create jks file through keytool in command prompt.
      
      keytool -genkey-v-keystore keystore.jks -alias spring -keyalg RSA -keysize 2048 -validity 10000
   
      Add password secret when prompted for a keystore password and Save this jks file into project /resources/saml/ directory
     
##### 19. Start your application then enter https://localhost:8080/v1/students url in browser. It will redirect to Okta login page. Enter your okta credential and you should get success response data
![alt text](https://user-images.githubusercontent.com/26097904/127321245-050c0225-7540-4eaa-8415-f2c9b3de79c2.png)

![alt text](https://user-images.githubusercontent.com/26097904/127321328-5c166b2f-fc90-4f7f-a4b9-a14d482fa120.png)




      
      
      
      





























