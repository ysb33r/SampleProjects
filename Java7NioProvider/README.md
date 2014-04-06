
The NIO.2 API introduced in the Java SE 7 release provides the ability to develop a custom file system provider that can
be used to manage file system objects.

+ *File system* - A container with organized, homogenous elements referred to as file system objects.
+ *File system object* - Can be a *File store*, *File*, or *Directory*.
+ *File store* - Volume or partition in which files are stored.
  + Windows: C:, D: are stores
  + FTP: Each host is a file store


The java.nio.file.spi.FileSystemProvider class allows you to develop a custom file system provider.
A custom file system provider is useful in the following situations:


This is what the [Java Documentation at Oracle](http://docs.oracle.com/javase/7/docs/technotes/guides/io/fsp/filesystemprovider.html)
states:

Overview of the java.nio.file.spi.FileSystemProvider Class
----------------------------------------------------------

A custom file system provider must implement the ```java.nio.file.spi.FileSystemProvider``` class.
A file system provider is identified by a URI scheme such as file, jar, memory, cd.

An implementation of the ```java.nio.file.spi.FileSystemProvider``` class is a factory for instances of the
```java.nio.file.FileSystem``` class.
A file system's URI has a URI scheme that matches the URI scheme of the file system provider that created it.

The ```newFileSystem``` method is used to create a file system and the ```getFileSystem``` method is used to retrieve a
reference to an existing file system.

A template for implementation - src/templates/FileSystemProvider.groovy

URI passing becomes a bit of fun
--------------------------------

Consider

```tar:ftp://host:21/path/remote-archive.tar!/a/file/inside/the/archive.txt```

Also see http://commons.apache.org/proper/commons-vfs/filesystems.html