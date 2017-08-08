package cn.edu.cup.os

import cn.edu.cup.userLibrary.UserClass
import cn.edu.cup.userLibrary.UserLibrary
import cn.edu.cup.userLibrary.UserLibraryClassify
import grails.converters.JSON

class Operation4UserLibraryController {

    /*
    * 选择
    * */

    /*
    *  统计
    * */

    def countUserClass() {
        def count = UserClass.count()
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 列表
    * */

    def listUserClass() {
        def userClassList = UserClass.list(params)
        if (request.xhr) {
            render(template: 'listUserClass', model: [userClassList: userClassList])
        } else {
            respond userClassList
        }
    }

    /*
    * 统计
    * */

    def countUserLibrary() {
        def count = UserLibrary.count()
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 列表
    * */

    def listUserLibrary() {
        def userLibraryList = UserLibrary.list(params)
        if (request.xhr) {
            render(template: 'listUserLibrary', model: [userLibraryList: userLibraryList])
        } else {
            respond userLibraryList
        }
    }

    /*
    * 统计
    * */

    def countUserLibraryClassify() {
        def count = UserLibraryClassify.count()
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 列表
    * */

    def listUserLibraryClassify() {
        def userLibraryClassifyList = UserLibraryClassify.list(params)
        if (request.xhr) {
            render(template: 'listUserLibraryClassify', model: [userLibraryClassifyList: userLibraryClassifyList])
        } else {
            respond userLibraryClassifyList
        }
    }

    def index() {}
}
