"# Java Training Classes"


1. Traverse through the given CSV and for each n lines create a separate txt file and store it into local storage."-completed

How to execute:
FileUtil is  standalone app.
After running which will ask for 3 things:
1>Which file to split
2>Where to store all the files
3>At what length the files need to expected to split

if we pass all these three things the files will split and store in the destination directory

Generics:

Having multiple Enums provide generic getEnumType method that
takes input value of the enum and return respective enum instance.

Collections:

Implementing LRU:

To  implementing an LRU cache  used doubly linked list and a hash map.

Doubly Linked List - List of pages with most recently used page at the start of the list.
 So, as more pages are added to the list, least recently used pages are moved to the end of the list with page at tail being the least recently used page in the list.

Hash Map (key: page number, value: page) - For O(1) access to pages in cache

When a page is accessed, there can be 2 cases:
1. Page is present in the cache - If the page is already present in the cache, we move the page to the start of the list.
2. Page is not present in the cache - If the page is not present in the cache, we add the page to the list.
How to add a page to the list:
   a. If the cache is not full, add the new page to the start of the list.
   b. If the cache is full, remove the last node of the linked list and move the new page to the start of the list


MultiThreading:

Take the given keywords and make wiki call using the given URL for each keyword. Each call respond with a json response. Parse the response and use the description to create a seperate text file and save to local storage.


 Traverse through the generated .txt files  from Task #2 and provide a counter for each distinct word.


TODO:

Multithreading:


4. Auto suggests on the words generated from Task #3."

Collections:

"1. Implementing the HR related App, with scheduling the interview for the scheduled and
non scheduled guys. Assuming that there is only one panel to interview.

Annotations:
"Given three related documents perform validation on the fields and field data types using annotations.
Take the given related documents as Document Set and perform Consistency check across the documents in document set."



