package org.ysb33r.groovy.niosample

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.transform.Synchronized

import java.nio.channels.SeekableByteChannel
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileAttribute
import java.nio.file.attribute.FileAttributeView

/**
 * Implements the TarFileSystemProvider for scheme 'X'
 */
@CompileStatic
class TarFileSystemProvider extends java.nio.file.spi.FileSystemProvider {

    private static final String DEFAULT_SCHEME = 'tar'
    private static final boolean BANGPATH = true

    TarFileSystemProvider() {super()}

    TarFileSystemProvider( final String s ) {
        super()
        scheme = s.toLowerCase()
    }
    /** Checks the existence, and optionally the accessibility, of a file.
     *
     * @param path
     * @param modes
     */
    @Override
    void checkAccess(Path path, AccessMode... modes) {

    }

    /** Copy a file to a target file
     *
     * @param source
     * @param target
     * @param options
     */
    @Override
    void copy(Path source, Path target, CopyOption... options) {

    }

    /** Creates a new directory.
     *
     * @param dir
     * @param attrs
     */
    @Override
    void createDirectory(Path dir, FileAttribute<?>... attrs) {

    }

    /** Deletes a file.
     *
     * @param path
     */
    @Override
    void delete(Path path) {

    }

    /** Returns a file attribute view of a given type.
     *
     * @param path
     * @param type
     * @param options
     * @return
     */
    @Override
    <V extends FileAttributeView> V getFileAttributeView(Path path, Class<V> type, LinkOption... options) {

    }

    /** Returns the FileStore representing the file store where a file is located.
     *
     * @param path
     * @return
     */
    @Override
    FileStore getFileStore(Path path) {

    }

    /** Returns an existing TarFileSystem created by this provider.
     *
     * @param uri
     * @return
     *
     * @throw FileSystemNotFoundException
     */
    @Override
    @Synchronized
    FileSystem getFileSystem(URI uri) {
        assert uri.scheme.toLowerCase() == scheme

        TarFileSystem ret = filesystems[fsKey(uri)]
        if(!ret) {
            throw new FileSystemNotFoundException()
        }

        return ret
    }

    /** Constructs a new TarFileSystem object identified by a URI.
     *
     * @param uri A URI starting with 'tar:'
     * @param env FTP properties
     *
     *
     * @return
     *
     * @throw FileSystemAlreadyExistsException (or deriviate)
     */
    @Override
    @Synchronized
    FileSystem newFileSystem(URI uri, Map<String,?> env) {
        assert uri.scheme.toLowerCase() == scheme

        Path p=fsKey(uri)
        TarFileSystem tfs = filesystems[p]

        if(tfs) {
            throw new FileSystemAlreadyExistsException()
        }

        tfs = new TarFileSystem(this,p,env)
        // For env, iterate through properties, setting up the FileSystemOptions

        filesystems[p] = tfs
        //
        return tfs
    }


    /** Return a Path object by converting the given URI.
     *
     * @param uri
     * @return
     */
    @Override
    Path getPath(URI uri) {

    }

    /** Returns the URI scheme that identifies this provider.
     *
     * @return
     */
    @Override
    String getScheme() { scheme }

    /** Tells whether or not a file is considered hidden.
     *
     * @param path
     * @return
     */
    @Override
    boolean isHidden(Path path) {

    }

    /** Tests if two paths locate the same file.
     *
     * @param path
     * @param path2
     * @return
     */
    @Override
    boolean isSameFile(Path path, Path path2) {

    }

    /** Move or rename a file to a target file.
     *
     * @param source
     * @param target
     * @param options
     */
    @Override
    void move(Path source, Path target, CopyOption... options) {

    }

    /** Opens or creates a file, returning a seekable byte channel to access the file.
     *
     */
    @Override
    SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs) {

    }

    /** Opens a directory, returning a DirectoryStream to iterate over the entries in the directory.
     *
     * @param dir
     * @param filter
     * @return
     */
    @Override
    DirectoryStream<Path> newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter) {

    }

    /** Reads a file's attributes as a bulk operation.
     *
     * @param path
     * @param type
     * @param options
     * @return
     */
    @Override
    <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options) {

    }

    /** Reads the target of a symbolic link.
     *
     * @param path
     * @param attributes
     * @param options
     * @return
     */
    @Override
    Map<String,Object> readAttributes(Path path, String attributes, LinkOption... options) {

    }

    /** Sets the value of a file attribute.
     *
     * @param path
     * @param attribute
     * @param value
     * @param options
     */
    @Override
    void setAttribute(Path path, String attribute, Object value, LinkOption... options) {

    }

    /** With a tar, the full path is the key
     *
     * @param uri
     * @return
     */
    Path fsKey(URI uri) {
        assert uri.scheme.toLowerCase() == scheme

        // TODO: This approach is lame, need to re-use what Apache VFS does to parse multiple combined URIs
        try {
            String path=uri.schemeSpecificPart
            if(BANGPATH) {
                // This code is wrong - it should find the last !
                int sep=path.indexOf("!/")
                if (sep != -1) {
                    path = path.substring(0, sep)
                }
            }

            def u2=URI.create(path)
            Path key
            if(u2.isOpaque() || u2.scheme != null) {
                key= Paths.get(path)
            } else if (u2.isAbsolute()) {
                key=Paths.get('file://' + path)
            } else {
                key= Paths.get('').resolve(path).toAbsolutePath()
            }

            return key
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }


    private String scheme = DEFAULT_SCHEME
    private Map<Path,TarFileSystem> filesystems = [:]

}
