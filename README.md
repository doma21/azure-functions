# azure-functions-Cart Transfer

This project provides an Azure HTTPTrigger that can ingest cart data from POQ and replies with the dummy response. This is not intended to be used as is in Production, but this demo project is only to highlight the usage of Azure functions and its ability to get Cart Data as Pojo object for further processing.

To build this
 # mvn clean package
To run this
 # mvn azure-functions:run

# Deployment to Azure
Current values are OS - Windows and Region - WestUS, if you need to make change update pom.xml or parameterize the pom.xml file to load from environment variables.
