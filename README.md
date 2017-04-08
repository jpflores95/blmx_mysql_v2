I. Overview

This app is a contact book. The basic function of this app is to store contacts in a database. An added feature would be that this app can also recite the name of the contact selected. This app can help with the registration of websites as well as being able to help the admins read user names properly with the text to speech feature.

II. Technology used
uses ClearDB (mySQL)
uses Text-to-Speech Watson Service

III. How to deploy in bluemix
create application
To create the application 
1. In Bluemix DevOps page, under MY PROJECTS, click START CODING.
2.	Enter project name and select LINK TO EXISTING GitHub REPOSITORY in this case the github link is https://github.com/jpflores95/blmx_mysql_v2 . Fill up the necessary information.
3.	Click the CREATE button for the project to be established. 

To sync the project with github
1.	Click the Git icon at the left to enter the Git page.

2.	Select all the changes and click commit button.
3.	Finally, push the committed changes

Creating the Build Stage

1.	Click the BUILD & DEPLOY button beside the EDIT button at the top. This brings us to the pipeline development page.

2.	Click ADD STAGE button.

3.	Enter the necessary information on the INPUT tab.
4.	On the JOBS tab, click ADD JOB button and select BUILD as job type. Set the values as the following picture. SAVE the settings.
Creating the Test Stage

1.	Click the BUILD & DEPLOY button next to the EDIT button at the top.

2.	Click ADD STAGE button.

3.	Enter the necessary information on the INPUT tab.
4.	On the JOBS tab, click ADD JOB button and select TEST as job type. Set the values as the following picture. SAVE the settings.

Creating the Deploy Stage

1.	Click the BUILD & DEPLOY button beside the EDIT button at the top.

2.	Click ADD STAGE button.

3.	Enter the necessary information on the INPUT tab.
4.	On the JOBS tab, click ADD JOB button and select DEPLOY as job type. Set the values as the following picture. SAVE the settings.

Application Deployment

A delivery pipeline is created for continuous integration as we added all stages: build, test, and deploy. In order to run our application, open the BUILD & DEPLOY page.

1.	Click RUN STAGE icon at the top right of the Build Stage. The other two stages will automatically run when the previous stage has passed.

2.	Wait until all the stages have a status of STAGE PASSED.

3.	The application is ready. We can now go to our application page.
4.	The application used is https://calculator-jpflores95.mybluemix.net/calculator.jsp



add the following services:
ClearDB (mySQL)
Watson Text-to-Speech service
restage app to apply the changes
go to http://lbyclds-jaecv2.mybluemix.net/home
