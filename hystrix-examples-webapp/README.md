## This is a spring boot based project configures SOAP and REST webservices along with Hystrix

1. Import project and build using gradle
2. Run Application.java file
3. Now hit REST using http://localhost:8080/to-read
4. Hit SOAP webservice using SOAP UI or Boomerang chrome extension with http://localhost:8080/ws URL and following SOAP request sample
    

``` <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gs="http://spring.io/guides/gs-producing-web-service">  <soapenv:Header/>  <soapenv:Body>    <gs:getCountryRequest>        <gs:name>Spain</gs:name>      </gs:getCountryRequest> </soapenv:Body></soapenv:Envelope>
'''

5. Now see hystrix dashboard at http://localhost:8080/hystrix then configure stream http://localhost:8080/hystrix.stream