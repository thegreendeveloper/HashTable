# HashTable
A simple implementation of a HashTable that use chained hashing


## Notes
The data structure make use of java.util.ArrayList to store objects. A custom hash node object was created in order to 
support the chained hashing. A tiny unit test was implemented. The implementation is about twice as slow as java.util.hashmap i.e.
there is definetly room for improvements. 

Potential optimizations:
- Changing the hash method
- Testing the solution with some other container than ArrayList
- Optimizing code
