# FormsGitJenkins

Agenda: 
1. Run tests on Jenkins with GitHub as the workspace source.
	Why- 
		Write new tests and merge them to the main repo. Track version changes. Access projects via Jenkins remotely
	How-
		Connect git to local machine. Sync with GitHub. Sync with Jenkins. Sync with VScode. Fix pom.xml file. Add external Jars
End goal- 
	CI/CD pipelines for the automated tests to run on each version release.
News-
	Success! Managed to run a Jenkins job with GitHub as a source, and HTML reports of ExtentReports! 


2. Run Jenkins independently as a service on qa19 with an ip address
Why- 
Have it continuesly available without the need of a person to run it in qa19 server. Run builds automatically with a Webhook on GitHub
