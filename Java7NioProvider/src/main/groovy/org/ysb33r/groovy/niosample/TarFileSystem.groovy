package org.ysb33r.groovy.niosample

import java.nio.file.FileStore
import java.nio.file.Path
import java.nio.file.PathMatcher
import java.nio.file.WatchService
import java.nio.file.attribute.UserPrincipalLookupService
import java.nio.file.spi.FileSystemProvider

class TarFileSystem extends java.nio.file.FileSystem {

    TarFileSystem( TarFileSystemProvider prv, Path p, Map<String, ?> env) {
        super()
        linkedProvider = prv
    }

    /** Closes this file system.
     *
     */
    @Override void close() {

    }

    /** Returns an object to iterate over the underlying file stores.
     *
     * @return
     */
    @Override
    Iterable<FileStore> getFileStores() {

    }

    /** Converts a path string, or a sequence of strings that when joined form a path string, to a Path.
     *
     * @param first
     * @param more
     * @return
     */
    @Override
    Path getPath(String first, String... more) {

    }

    /** Returns a PathMatcher that performs match operations on the String representation of Path objects by interpreting a given pattern.
     *
     * @param syntaxAndPattern
     * @return
     */
    @Override
    PathMatcher getPathMatcher(String syntaxAndPattern) {

    }

    /** Returns an object to iterate over the paths of the root directories.
     *
     * @return
     */
    @Override
    Iterable<Path> getRootDirectories() {

    }

    /** Returns the name separator, represented as a string.
     *
     * @return
     */
    @Override
    String getSeparator() {

    }

    /** Returns the UserPrincipalLookupService for this file system (optional operation).
     *
     * @return
     */
    @Override
    UserPrincipalLookupService getUserPrincipalLookupService() {

    }

    /** Tells whether or not this file system is open.
     *
     * @return
     */
    @Override
    boolean isOpen() {

    }

    /** Tells whether or not this file system allows only read-only access to its file stores.
     *
     * @return
     */
    @Override
    boolean isReadOnly() {

    }

    /** Constructs a new WatchService (optional operation).
     *
     * @return
     */
    @Override
    WatchService newWatchService() {

    }

    /** Returns the provider that created this file system.
     *
     * @return
     */
    @Override
    FileSystemProvider provider() { linkedProvider }

    /** Returns the set of the names of the file attribute views supported by this TarFileSystem.
     *
     * @return
     */
    @Override
    Set<String> supportedFileAttributeViews() {

    }

    private TarFileSystemProvider linkedProvider
}
