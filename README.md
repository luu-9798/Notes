# Note App
This app allows users to create and save notes on their Android device.

# Features
1. Create a note with a title and content
2. View all saved notes

# Screens
1. Create Note: This screen allows users to create a new note by entering a title and the note's content. The user can then save the note.
2. View Notes: This screen displays all of the notes that have been saved by the user. Each note is listed with its title and a preview of its content. The user can tap on a note to view or edit it.

# Database
This app uses Room Database to store the Note object, which consists of three variables: id, title, and content.

#Room provides an abstraction layer over SQLite, making it easier to work with databases and eliminating the need for writing raw SQL queries.

## Note Entity
The Note entity is defined with the following variables:
1. id: a unique identifier for each note.
2. title: the title of the note.
3. content: the content of the note.

## DAO (Data Access Object)
A DAO (Data Access Object) is an interface that defines the methods that access the database. In this app, the NoteDao interface is used to perform CRUD operations on the Note entity.

## Room Database
The Room Database class is annotated with @Database, and it includes the entities and DAOs that it uses. The Room Database is instantiated using the singleton pattern to ensure that only one instance of the database is created for the entire app.

