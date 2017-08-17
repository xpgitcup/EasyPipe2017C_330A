package cn.edu.cup.dictionary

import grails.test.mixin.*
import spock.lang.*

@TestFor(DataItemAController)
@Mock(DataItemA)
class DataItemAControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.dataItemAList
            model.dataItemACount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.dataItemA!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def dataItemA = new DataItemA()
            dataItemA.validate()
            controller.save(dataItemA)

        then:"The create view is rendered again with the correct model"
            model.dataItemA!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            dataItemA = new DataItemA(params)

            controller.save(dataItemA)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/dataItemA/show/1'
            controller.flash.message != null
            DataItemA.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def dataItemA = new DataItemA(params)
            controller.show(dataItemA)

        then:"A model is populated containing the domain instance"
            model.dataItemA == dataItemA
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def dataItemA = new DataItemA(params)
            controller.edit(dataItemA)

        then:"A model is populated containing the domain instance"
            model.dataItemA == dataItemA
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/dataItemA/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def dataItemA = new DataItemA()
            dataItemA.validate()
            controller.update(dataItemA)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.dataItemA == dataItemA

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            dataItemA = new DataItemA(params).save(flush: true)
            controller.update(dataItemA)

        then:"A redirect is issued to the show action"
            dataItemA != null
            response.redirectedUrl == "/dataItemA/show/$dataItemA.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/dataItemA/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def dataItemA = new DataItemA(params).save(flush: true)

        then:"It exists"
            DataItemA.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(dataItemA)

        then:"The instance is deleted"
            DataItemA.count() == 0
            response.redirectedUrl == '/dataItemA/index'
            flash.message != null
    }
}
