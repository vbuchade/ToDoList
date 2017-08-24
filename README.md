# ToDoList
Android app for Todo List

# Pre-work - *Name of App Here*

**ToDoList** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Varsha P. Buchade**

Time spent: **9** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **successfully add and remove items** from the todo list
* [X] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [X] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [ ] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [X] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [ ] Add support for completion due dates for todo items (and display within listview item)
* [ ] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [ ] Add support for selecting the priority of each todo item (and display in listview item)
* [X] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [X] List anything else that you can get done to improve the app functionality!
* Error handling so that empty items will not be added to the todo-list.
* Capitalized first letter of the to-do list item added by user.
* Once the user clicks on "Add" or "Save" button the soft-keyboard closes thus giving user bigger view of all the items in list.


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/vbuchade/ToDoList/blob/master/GIF_ToDoListAPP.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** Getting started with Android development was quite easy-going. Drag-n-drop feature for UI-components, development studio is easy to ramp-up as compared to building HTML/CSS/JS UI from scratch.
Since I have worked with java, Android-development was easy to pick up. One can see the connection between xml layouts and HTML files. However the styling flexibility offered by css to apply same set-of-styles to multiple UI-components is something I did not see in Android.
Activity lifecycle seems intuitive.

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** ArrayAdapter is a powerful interface to work with list-view since an app developer will work often with list of data objects. It maps the content(Model) to the UI-listview (View). The controller part is implemented by the activity life-cycle methods.
  Adapter is important in the sense it can be used to map objects with simple/complex internal structure to the view easily. The component based approach separates the unit functionality thus making the development easier.
  convertView acts as a handle to the list-view component in activity-layout. It is used to map the custom designed layout of each list-item from CustomAdapter into the list-view.

## Notes

Describe any challenges encountered while building the app.

* Once I changed todo-list data structure from list of strings to list of ToDoItem objects, I had to figure out how to write complex objects into files and retrieve them properly back to the list of objects.
* Additionally, my app started crashing with error = "Client not ready yet..Waiting for process to come online". Upon debugging, I discovered this was happening because I was not creating a "new" object before adding data from EditText to the ArrayList<ToDoItem>.

## License

MIT License

Copyright (c) 2017

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.