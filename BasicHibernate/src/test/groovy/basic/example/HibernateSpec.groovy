package basic.example

import spock.lang.*
import grails.gorm.annotation.Entity
import grails.transaction.Rollback
import org.grails.orm.hibernate.HibernateDatastore
import org.springframework.transaction.PlatformTransactionManager
import org.grails.datastore.mapping.core.DatastoreUtils

class ExampleSpec extends Specification {

    @Shared @AutoCleanup HibernateDatastore hibernateDatastore
    @Shared PlatformTransactionManager transactionManager
    @Shared Map configuration = [
      'hibernate.hbm2ddl.auto':'create-drop',
      'dataSource.url':'jdbc:h2:mem:myDB'
    ]
    
    void setupSpec() {
       hibernateDatastore = new HibernateDatastore(DatastoreUtils.createPropertyResolver(configuration),FileStatus)
       transactionManager = hibernateDatastore.getTransactionManager()
    }

    @Rollback
    void "test execute GORM standalone in a unit test"() {
       when:
	new FileStatus( name : 'foo', location : 'bar' ).save()

       then:
       FileStatus.findByName('foo')?.downloaded == false
    }
}
