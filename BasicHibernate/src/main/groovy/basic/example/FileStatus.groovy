package basic.example

import grails.gorm.annotation.Entity

@Entity
class FileStatus  {
    String name
    String location
    boolean downloaded = false
    boolean processed = false

    static constraints = {

        name unique : true
        location blank : true
    }
}

