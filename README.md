# BotsCrewTask

INSTRUCTIONS
<br><b>all books</b> - shows you all books.
<br><b>add <i>{Author Name} {Book Name}</i> (or <i>{Book Name} {Author Name}</i>)</b> - adds new book (if book with same name and author not exists).
<br><b>remove <i>{Book Name}</i></b> - removes book from database. If database contains more than one book with {Book Name} - u may get all list of books with this name and then choose concrete book by number.
<br><b>edit <i>{Book Name}</i></b> - edits book in database. If database contains more than one book with {Book Name} - u may get all list of books with this name and then choose concrete book by number. Also, if concrete author already has book with updated {Book Name} - you'll get a corresponding message.

SMALL EXAMPLE
<br>All books
<br>Unknown operation. Please, try again!
<br>all books
<br>Our books:
<br>сен 20, 2017 5:42:20 AM org.hibernate.hql.internal.QueryTranslatorFactoryInitiator initiateService
INFO: HHH000397: Using ASTQueryTranslatorFactory
<br>&emsp;	Not yet, but you may add some
<br>add Some Author
<br>Book name must be in quotes
<br>add Some Author "H"a
<br>Book name must be greater than zero
<br>add Some Author "Harry Potter"
<br>book  Some Author  Harry Potter was added
<br>add Some Author "Harry Potter"
<br>book with author  Some Author  and name Harry Potter already exists
<br>add Unknown "Other book"
<br>book  Unknown  Other book was added
<br>all books
<br>Our books:
<br>&emsp;	 Some Author  Harry Potter
<br>&emsp;	 Unknown  Other book
<br>add Unknown "Harry Potter"
<br>book  Unknown  Harry Potter was added
<br>all books
<br>Our books:
<br>&emsp;	 Some Author  Harry Potter
<br>&emsp;	 Unknown  Harry Potter
<br>&emsp;	 Unknown  Other book
<br>edit Other book
<br>Please, write new book name
<br>Updated other book
<br>Book  Unknown  Updated other book was edited. New name - Updated other book
<br>all books
<br>Our books:
<br>&emsp;	 Some Author  Harry Potter
<br>&emsp;	 Unknown  Harry Potter
<br>&emsp;	 Unknown  Updated other book
<br>edit Harry Potter
<br>We have few books with such name. Please, choose one by typing a number of book
<br>&emsp;	1.  Some Author  "Harry Potter"
<br>&emsp;	2.  Unknown  "Harry Potter"
<br>2
<br>Please, write new book name
<br>H P
<br>Book  Some Author  Harry Potter was edited. New name - H P
<br>all books
<br>Our books:
<br>&emsp;	 Unknown  H P
<br>&emsp;	 Some Author  Harry Potter
<br>&emsp;	 Unknown  Updated other book
<br>adit Updated other book
<br>Unknown operation. Please, try again!
<br>edit Updated other book
<br>Please, write new book name
<br>H P
<br>Cannot update book, because this author already has this book
<br>add Some Author "H P"
<br>book  Some Author  H P was added
<br>all books
<br>Our books:
<br>&emsp;	 Unknown  H P
<br>&emsp;	 Some Author  H P
<br>&emsp;	 Some Author  Harry Potter
<br>&emsp;	 Unknown  Updated other book
<br>remove H P
<br>We have few books with such name. Please, choose one by typing a number of book
<br>&emsp;	1.  Unknown  "H P"
<br>&emsp;	2.  Some Author  "H P"
<br>1
<br>Book  Unknown  H P was removed
<br>all books
<br>Our books:
<br>&emsp;	 Some Author  H P
<br>&emsp;	 Some Author  Harry Potter
<br>&emsp;	 Unknown  Updated other book
<br>remove Harry Potter
<br>Book  Some Author  Harry Potter was removed
<br>remove Updated other book
<br>Book  Unknown  Updated other book was removed
<br>remove H P
<br>Book  Some Author  H P was removed
<br>all books
<br>Our books:
<br>	Not yet, but you may add some
