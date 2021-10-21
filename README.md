# Help-Rules-book-plugin

## How to use

To use the book in-game, you simply need to send `/new` command, players need `HelpRulesBook.use` permission to use

Admin can reload the book from configuration with `/new reload` command, they need `HelpRulesBook.admin` permission

## How to personalize

Insert a title and an author, this is useless but necessary because the book won't be display in game without.

Then insert elements in pages list, 1 element of the list is dedicated to 1 page each.

You can help yourself by seeing example in config.yml file, but keep warning cause syntax is bery case sensitive.

You can show messages by hovering the mouse and/or by clicking on them, if you want an area to be clickeable without showing a text in a tooltip, just don't add message between comma BUT don't remove comma.

Please DON'T add space between comma.

You can replace true by false to disable clicking action without removing the content.

You can escape { or } characters by adding an \\ before them

Please 

### Syntax

`{Text displaying in page in any circonstance,Hover text,true(action_type.action_information)}`

#### Examples

`{go back to the first page,click to change page,true(CHANGE_PAGE.1)}`

`{/spawn,Click to return to spawn,true(RUN_COMMAND./spawn)}`

`{Visit your website,Click to open in your web browser,true(OPEN_URL.https://github.com/quentinlegot)}`

SUGGEST_COMMAND don't work in 1.14 (we completely don't know why)

## Licence

Free use under MIT Licence

## Issues, Fork and PR

Project is inactive so issues won't reviewed

Fork are encouraged, really, take our project, improve it, maybe adapt for newly versions of minecraft and use it on your server (be careful to respect the licence).

Pull requests may be reviewed and merge into master branch but I can't promise you, I currently haven't time to test.
