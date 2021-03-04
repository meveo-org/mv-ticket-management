# mv-ticket-management
trouble ticket management system

## installation
In meveo, install (Configuration/Modules/Import From File) the CREDENTIALS module then the latest version of mv-ticket-management module.


## Project

A project, or space is an entity that hold a list of milestones and tickets

Ticket can be retrieved from assembla and pushed to github

### Import milestones and tickets from assembla

In assembla (Profile/API Applications & Sessions) create or reuse a personal key that have Api access, note its <Key> and <Secret>
then note the name of your project as it appear in `https://app.assembla.com/spaces/<assemblaProjectName>` 
 
In meveo Create a credential (INFRA/Credential) with
 * DOMAIN: assembla.com
 * USERNAME: <Key>
 * AUTHENTICATION_TYPE: Header Token (HEADER)
 * HEADER_KEY: X-API-KEY
 * HEADER_VALUE: <Secret>

Then create a project (Projects/Project/ New), give it a name and add a Remote space with 
 * Key : assembla.com
 * Value : <assemblaProjectName>
then save it

Go to the detail page of the project and click on `retrieve milestone` to create on meveo the milestones from the assembla ones

check in (Projects/Milestone) that all your milestones are there. You can delete the one you are not interested in

then go to the detail page of the project and click on `retrieve milestone tickets` to create on meveo the tickets for each of the milestones (note that tickets without milestones are not retrieved)

### Export milestones and ticket to a github repository

In github go to your account Settings/Developper Settings/Personal access Token and create a token that has the permission to create issues, note its <Token>
Note the name of your repo <fullRepoName> it is a composed name username/reponame accessible at https://github.com/username/reponame (ex: smichea/assembla-api)
or organisationName/repoName for a repo in an organisation
 
In meveo Create a credential (INFRA/Credential) with
 * DOMAIN: github.com
 * USERNAME: your github username (not really used)
 * AUTHENTICATION_TYPE: OAuth2 (OAUTH2)
 * TOKEN: <Tocken>

Then create a project (Projects/Project/ New), give it a name and add a Remote space with 
 * Key : github.com
 * Value : <fullRepoName>
then save it

then go to the detail page of the project and click on `create tickets in github` to create the milestones and tickets on github.

## Ticket

A ticket represents an issue, a feature, a task,...

It is currently persisted in database but in a later version will be persisted as a json file in the module git repo the project belongs to

The file is stored in a directory `/issues/<milestone_id>` located at the root of the module
where `<milestone_id>` is the id of the milestone associated to the ticket.

The name of the file is `<number>-<title>.json`
where `<number>` is the identifier (a sequence) and `title` the title of the ticket.

Content of the json file:
```
{
    "state": "open",
    "description": "I'm having a problem with this.",
    "closedAt": null,
    "createdAt": "2011-04-22T13:33:48Z",
    "updatedAt": "2011-04-22T13:33:48Z",
    "creator": "user0",
     "tags": ["tag1","tag2"],
     "assignees": ["user1","user2"]
     "comments" : [
         {
            "createdAt": "2011-04-22T13:33:48Z",
            "updatedAt": "2011-04-22T13:33:48Z",
            "creator": "user0",
            "description": "This is my comment."
         },
         {
            "createdAt": "2011-04-22T13:33:48Z",
            "updatedAt": "2011-04-22T13:33:48Z",
            "creator": "user1",
            "description": "I agree with it."
         }
     ]
}
```
