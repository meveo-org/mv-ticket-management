# mv-ticket-management
trouble ticket management system

## Datamodel

### Project

A project, or space is an entity that hold a list of milestones and tickets



### Ticket

A ticket represents an issue, a feature, a task,...

It is persisted as a json file in the module git repo

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

This data mode is inspired from

github example

```
[
  {
    "id": 1,
    "node_id": "MDU6SXNzdWUx",
    "url": "https://api.github.com/repos/octocat/Hello-World/issues/1347",
    "repository_url": "https://api.github.com/repos/octocat/Hello-World",
    "labels_url": "https://api.github.com/repos/octocat/Hello-World/issues/1347/labels{/name}",
    "comments_url": "https://api.github.com/repos/octocat/Hello-World/issues/1347/comments",
    "events_url": "https://api.github.com/repos/octocat/Hello-World/issues/1347/events",
    "html_url": "https://github.com/octocat/Hello-World/issues/1347",
    "number": 1347,
    "state": "open",
    "title": "Found a bug",
    "body": "I'm having a problem with this.",
    "user": {
      "login": "octocat",
      "id": 1,
      "node_id": "MDQ6VXNlcjE=",
      "avatar_url": "https://github.com/images/error/octocat_happy.gif",
      "gravatar_id": "",
      "url": "https://api.github.com/users/octocat",
      "html_url": "https://github.com/octocat",
      "followers_url": "https://api.github.com/users/octocat/followers",
      "following_url": "https://api.github.com/users/octocat/following{/other_user}",
      "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
      "organizations_url": "https://api.github.com/users/octocat/orgs",
      "repos_url": "https://api.github.com/users/octocat/repos",
      "events_url": "https://api.github.com/users/octocat/events{/privacy}",
      "received_events_url": "https://api.github.com/users/octocat/received_events",
      "type": "User",
      "site_admin": false
    },
    "labels": [
      {
        "id": 208045946,
        "node_id": "MDU6TGFiZWwyMDgwNDU5NDY=",
        "url": "https://api.github.com/repos/octocat/Hello-World/labels/bug",
        "name": "bug",
        "description": "Something isn't working",
        "color": "f29513",
        "default": true
      }
    ],
    "assignee": {
      "login": "octocat",
      "id": 1,
      "node_id": "MDQ6VXNlcjE=",
      "avatar_url": "https://github.com/images/error/octocat_happy.gif",
      "gravatar_id": "",
      "url": "https://api.github.com/users/octocat",
      "html_url": "https://github.com/octocat",
      "followers_url": "https://api.github.com/users/octocat/followers",
      "following_url": "https://api.github.com/users/octocat/following{/other_user}",
      "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
      "organizations_url": "https://api.github.com/users/octocat/orgs",
      "repos_url": "https://api.github.com/users/octocat/repos",
      "events_url": "https://api.github.com/users/octocat/events{/privacy}",
      "received_events_url": "https://api.github.com/users/octocat/received_events",
      "type": "User",
      "site_admin": false
    },
    "assignees": [
      {
        "login": "octocat",
        "id": 1,
        "node_id": "MDQ6VXNlcjE=",
        "avatar_url": "https://github.com/images/error/octocat_happy.gif",
        "gravatar_id": "",
        "url": "https://api.github.com/users/octocat",
        "html_url": "https://github.com/octocat",
        "followers_url": "https://api.github.com/users/octocat/followers",
        "following_url": "https://api.github.com/users/octocat/following{/other_user}",
        "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
        "organizations_url": "https://api.github.com/users/octocat/orgs",
        "repos_url": "https://api.github.com/users/octocat/repos",
        "events_url": "https://api.github.com/users/octocat/events{/privacy}",
        "received_events_url": "https://api.github.com/users/octocat/received_events",
        "type": "User",
        "site_admin": false
      }
    ],
    "milestone": {
      "url": "https://api.github.com/repos/octocat/Hello-World/milestones/1",
      "html_url": "https://github.com/octocat/Hello-World/milestones/v1.0",
      "labels_url": "https://api.github.com/repos/octocat/Hello-World/milestones/1/labels",
      "id": 1002604,
      "node_id": "MDk6TWlsZXN0b25lMTAwMjYwNA==",
      "number": 1,
      "state": "open",
      "title": "v1.0",
      "description": "Tracking milestone for version 1.0",
      "creator": {
        "login": "octocat",
        "id": 1,
        "node_id": "MDQ6VXNlcjE=",
        "avatar_url": "https://github.com/images/error/octocat_happy.gif",
        "gravatar_id": "",
        "url": "https://api.github.com/users/octocat",
        "html_url": "https://github.com/octocat",
        "followers_url": "https://api.github.com/users/octocat/followers",
        "following_url": "https://api.github.com/users/octocat/following{/other_user}",
        "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
        "organizations_url": "https://api.github.com/users/octocat/orgs",
        "repos_url": "https://api.github.com/users/octocat/repos",
        "events_url": "https://api.github.com/users/octocat/events{/privacy}",
        "received_events_url": "https://api.github.com/users/octocat/received_events",
        "type": "User",
        "site_admin": false
      },
      "open_issues": 4,
      "closed_issues": 8,
      "created_at": "2011-04-10T20:09:31Z",
      "updated_at": "2014-03-03T18:58:10Z",
      "closed_at": "2013-02-12T13:22:01Z",
      "due_on": "2012-10-09T23:39:01Z"
    },
    "locked": true,
    "active_lock_reason": "too heated",
    "comments": 0,
    "pull_request": {
      "url": "https://api.github.com/repos/octocat/Hello-World/pulls/1347",
      "html_url": "https://github.com/octocat/Hello-World/pull/1347",
      "diff_url": "https://github.com/octocat/Hello-World/pull/1347.diff",
      "patch_url": "https://github.com/octocat/Hello-World/pull/1347.patch"
    },
    "closed_at": null,
    "created_at": "2011-04-22T13:33:48Z",
    "updated_at": "2011-04-22T13:33:48Z",
    "repository": {
      "id": 1296269,
      "node_id": "MDEwOlJlcG9zaXRvcnkxMjk2MjY5",
      "name": "Hello-World",
      "full_name": "octocat/Hello-World",
      "owner": {
        "login": "octocat",
        "id": 1,
        "node_id": "MDQ6VXNlcjE=",
        "avatar_url": "https://github.com/images/error/octocat_happy.gif",
        "gravatar_id": "",
        "url": "https://api.github.com/users/octocat",
        "html_url": "https://github.com/octocat",
        "followers_url": "https://api.github.com/users/octocat/followers",
        "following_url": "https://api.github.com/users/octocat/following{/other_user}",
        "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
        "organizations_url": "https://api.github.com/users/octocat/orgs",
        "repos_url": "https://api.github.com/users/octocat/repos",
        "events_url": "https://api.github.com/users/octocat/events{/privacy}",
        "received_events_url": "https://api.github.com/users/octocat/received_events",
        "type": "User",
        "site_admin": false
      },
      "private": false,
      "html_url": "https://github.com/octocat/Hello-World",
      "description": "This your first repo!",
      "fork": false,
      "url": "https://api.github.com/repos/octocat/Hello-World",
      "archive_url": "https://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}",
      "assignees_url": "https://api.github.com/repos/octocat/Hello-World/assignees{/user}",
      "blobs_url": "https://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}",
      "branches_url": "https://api.github.com/repos/octocat/Hello-World/branches{/branch}",
      "collaborators_url": "https://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}",
      "comments_url": "https://api.github.com/repos/octocat/Hello-World/comments{/number}",
      "commits_url": "https://api.github.com/repos/octocat/Hello-World/commits{/sha}",
      "compare_url": "https://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}",
      "contents_url": "https://api.github.com/repos/octocat/Hello-World/contents/{+path}",
      "contributors_url": "https://api.github.com/repos/octocat/Hello-World/contributors",
      "deployments_url": "https://api.github.com/repos/octocat/Hello-World/deployments",
      "downloads_url": "https://api.github.com/repos/octocat/Hello-World/downloads",
      "events_url": "https://api.github.com/repos/octocat/Hello-World/events",
      "forks_url": "https://api.github.com/repos/octocat/Hello-World/forks",
      "git_commits_url": "https://api.github.com/repos/octocat/Hello-World/git/commits{/sha}",
      "git_refs_url": "https://api.github.com/repos/octocat/Hello-World/git/refs{/sha}",
      "git_tags_url": "https://api.github.com/repos/octocat/Hello-World/git/tags{/sha}",
      "git_url": "git:github.com/octocat/Hello-World.git",
      "issue_comment_url": "https://api.github.com/repos/octocat/Hello-World/issues/comments{/number}",
      "issue_events_url": "https://api.github.com/repos/octocat/Hello-World/issues/events{/number}",
      "issues_url": "https://api.github.com/repos/octocat/Hello-World/issues{/number}",
      "keys_url": "https://api.github.com/repos/octocat/Hello-World/keys{/key_id}",
      "labels_url": "https://api.github.com/repos/octocat/Hello-World/labels{/name}",
      "languages_url": "https://api.github.com/repos/octocat/Hello-World/languages",
      "merges_url": "https://api.github.com/repos/octocat/Hello-World/merges",
      "milestones_url": "https://api.github.com/repos/octocat/Hello-World/milestones{/number}",
      "notifications_url": "https://api.github.com/repos/octocat/Hello-World/notifications{?since,all,participating}",
      "pulls_url": "https://api.github.com/repos/octocat/Hello-World/pulls{/number}",
      "releases_url": "https://api.github.com/repos/octocat/Hello-World/releases{/id}",
      "ssh_url": "git@github.com:octocat/Hello-World.git",
      "stargazers_url": "https://api.github.com/repos/octocat/Hello-World/stargazers",
      "statuses_url": "https://api.github.com/repos/octocat/Hello-World/statuses/{sha}",
      "subscribers_url": "https://api.github.com/repos/octocat/Hello-World/subscribers",
      "subscription_url": "https://api.github.com/repos/octocat/Hello-World/subscription",
      "tags_url": "https://api.github.com/repos/octocat/Hello-World/tags",
      "teams_url": "https://api.github.com/repos/octocat/Hello-World/teams",
      "trees_url": "https://api.github.com/repos/octocat/Hello-World/git/trees{/sha}",
      "clone_url": "https://github.com/octocat/Hello-World.git",
      "mirror_url": "git:git.example.com/octocat/Hello-World",
      "hooks_url": "https://api.github.com/repos/octocat/Hello-World/hooks",
      "svn_url": "https://svn.github.com/octocat/Hello-World",
      "homepage": "https://github.com",
      "language": null,
      "forks_count": 9,
      "stargazers_count": 80,
      "watchers_count": 80,
      "size": 108,
      "default_branch": "master",
      "open_issues_count": 0,
      "is_template": true,
      "topics": [
        "octocat",
        "atom",
        "electron",
        "api"
      ],
      "has_issues": true,
      "has_projects": true,
      "has_wiki": true,
      "has_pages": false,
      "has_downloads": true,
      "archived": false,
      "disabled": false,
      "visibility": "public",
      "pushed_at": "2011-01-26T19:06:43Z",
      "created_at": "2011-01-26T19:01:12Z",
      "updated_at": "2011-01-26T19:14:43Z",
      "permissions": {
        "admin": false,
        "push": false,
        "pull": true
      },
      "allow_rebase_merge": true,
      "template_repository": null,
      "temp_clone_token": "ABTLWHOULUVAXGTRYU7OC2876QJ2O",
      "allow_squash_merge": true,
      "delete_branch_on_merge": true,
      "allow_merge_commit": true,
      "subscribers_count": 42,
      "network_count": 0,
      "license": {
        "key": "mit",
        "name": "MIT License",
        "url": "https://api.github.com/licenses/mit",
        "spdx_id": "MIT",
        "node_id": "MDc6TGljZW5zZW1pdA==",
        "html_url": "https://github.com/licenses/mit"
      },
      "forks": 1,
      "open_issues": 1,
      "watchers": 1
    },
    "author_association": "COLLABORATOR"
  }
]
```

assembla example 

 | Param | Data type | Access | Description |
 | :------ | :--- | :---: | :--------- |
 | id | string |	read |	Ticket identifier |
 | number | integer | read/write |	Ticket number, can be set ticket on creation|
 | summary |	string |	read/write |	Summary of the ticket, only field required to create a ticket.|
 | description |	text |	read/write |	Ticket description|
| priority |	integer |	read/write |	Ticket priority, possible values are bolded:<br/>    1 - Highest<br/>   2 - High<br/>    3 - Normal<br/>    4 - Low<br/>    5 - Lowest |
| completed_date |	datetime |	read |	Date and time when ticket was moved to one of Closed statuses (Fixed/Invalid).|
| component_id |	integer |	read/write |	Id of the component associated with the ticket|
| created_on |	datetime |	read/write |	Date and time when ticket was created, can be set ticket on creation|
| permission_type |	integer |	read/write |	Sets ticket as a development or a support Public/Private ticket. To enable support tickets, a Support Tool must be added to the project. Available values are in bold:<br/>    0 - Development ticket<br/>    1 - Private support ticket<br/>    2 - Public support ticket|
| importance |	float |	read |	Sorting criteria for Assembla Planner|
| is_story |	boolean(0,1) |	read/write |	Mark ticket as a story, this is a general organization option, it will not affect tickets directly unless you manage it in your app.|
| milestone_id |	integer |	read/write |	Id of the milestone that ticket is associated with|
| tags |	array |	write |	Associate tags to the ticket.<br/> Examples:<br/>`{"ticket":{"tags":["tag1","tag2"]}}`<br/>Query string:<br/>`ticket[tags][]=tag1&ticket[tags][]=tag2`|
| followers |	array |	write |	Add followers to the ticket by user login, email or id, examples:<br/> Examples:<br/>`{"ticket":{"followers":["a_user_login","user@example.com","bgnP_qA1Gr2QjIaaaHk9wZ"]}}`<br/>Query string:<br/>`ticket[followers][]=a_user_login&ticket[followers][]=user@example.com&ticket[followers][]=bgnP_qA1Gr2QjIaaaHk9wZ`|
| notification_list |	text |	read/write |	A list of user IDs and emails separated by a comma, be careful when updating this field, any update operation will totally rewrite the field, you have to programmatically get the existing list, append your data to it and update the field with the full list.<br/> Example list:<br/> `bgnP_qA1Gr2QjIaaaHk9wZ,user@example.com,ngngqA1Gr2QjIaaaHkhw0`|
| space_id |	string |	read |	ID of the space that ticket is associated with|
| state |	integer |	read |	Two values are available:<br/>    0 - Closed ticket<br/>    1 - Opened ticket|
| status |	string |	read/write |	String value of ticket status, set it to change the status of a ticket, not case sensitive i.e. New can be set as new or NEW<br/>Note: This field is writable on update operations only, not modifiable on ticket creation, this is restricted to preserve UI behavior.|
| story_importance |	integer |	read/write 	Importance of a ticket inside a story|
|updated_at |	datetime |	read |	Date and time ticket was updated.|
| working_hours |	float |	read/write |	Working hours remained for the ticket.|
| estimate |	float |	read/write |	Value of ticket estimation points|
| total_estimate |	float |	read |	If ticket is story, this field contains all estimate data collected from associated tickets|
| total_invested_hours | 	float |	read |	Hours invested in ticket.|
| total_working_hours |	float |	read |	Hours specified for this ticket to be invested for work on it.|
| assigned_to_id |	string |	read/write |	ID of user ticket is assigned to.|
| reporter_id |	string |	read/write |	ID of user who reported the ticket, can be set on ticket creation by a user with all permissions in space|
| custom_fields |	hash | read/write |	An array with custom fields and their values. To provide custom fields on create/update operations, add the custom fields collection with values e.g.:<br/>`{"ticket":{"custom_fields":{"Field name":"MyField"}}}`<br/>Query string:<br/>`ticket[custom_fields][Field name]=MyField`<br/>Note: Spaces in field names are converted to dashes in XML. So if custom field name is "Field name" it will be displayed "Field-name" in XML responses, if field name should be provided for create/update operations spaces should be replaced with dashes for XML(Field-name). More information on ticket create and ticket update documentation pages.|
| hierarchy_type |	integer |	read/write |	Plan level. Available values are in bold:<br/>    0 - No plan level<br/>    1 - Subtask<br/>    2 - Story<br/>    3 - Epic|
| is_support |	boolean |	read/write | Virtual attribute used for support tickets creation. Pass true if you want the ticket to be created with Support tool permissions. |
