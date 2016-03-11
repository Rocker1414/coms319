Three classes were used for this solution: Library, Shelf, and Book.
When the code is executed, a library is created and populated with shelves and books. The library
keeps track of an array of shelves, and each shelf keeps track of an array of books. The features
implemented in this web page include-

Search for a book: Enter the title of a book. All shelves in the library are searched for the book.
The first shelf containing the book is returned, even if there are duplicated.

Clicking on books: returns the JSON string of all the elements of a book, which were decided as
title, author, and number of pages. More could easily be added.

List shelves: Lists all the shelves in the library. Clicking on a shelf will display all books on the shelf

Show library: Shows all shelves and books in the library, and again books can be clicked to display their data.