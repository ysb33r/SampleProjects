package org.ysb33r.groovy.niosample

import spock.lang.*

import java.nio.file.FileSystemNotFoundException
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.spi.FileSystemProvider

/**
 * Created by schalkc on 06/04/2014.
 */
class TarFileSystemProviderSpec extends Specification {

    static final String TESTARCHIVE = 'tar:src/test/resources/test-files.tar'
    static final URI TESTURI = URI.create(TESTARCHIVE)

    def "Did my provider load?"() {
      given:
        def ip = FileSystemProvider.installedProviders()

      expect:
       FileSystemProvider.installedProviders().find { it.scheme == 'tar' }
    }

    def "Can I create a new filesystem?" () {
        given:
        def fs = FileSystems.newFileSystem (TESTURI,[:])

        expect:
        fs != null
        fs.provider().scheme == 'tar'
        false
    }

    def "Can I obtain the filesystem for a non-exiting filesystem?" () {
        when:
            def fs = FileSystems.getFileSystem (URI.create('tar:/somefile/that/does/not/exist.tar'))

        then:
            thrown(FileSystemNotFoundException)
    }
}
