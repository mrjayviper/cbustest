List of code that's been created
- Sling configuration + JSON file to set initial values
- Services to access this configuration
- a Sling model to retrieve the values for the dropdown list in the form
- a Sling servlet so the HTML can retrieve the values from the CBUS URL
- various utility classes to store common functions
- a UI component for displaying the form and results
- a JS file that's being inserted into the page via clientlibs

------

Notes
- For the servlet to work, "/bin/" path must exists in Apache Sling Servlet/Script Resolver (/system/console/configMgr)
- The output is displayed via a component called Pets. Please enable this component by adding the CBUS group in design mode