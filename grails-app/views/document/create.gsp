<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'document.label', default: 'Document')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-document" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-document" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.document}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.document}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">
                <fieldset class="form">
                    <!---->
                    <div class='fieldcontain required'>
                        <label for='name'>Name
                            <span class='required-indicator'>*</span>
                        </label><input type="text" name="name" value="" required="" maxlength="200" id="name"/>
                    </div>

                    <div class='fieldcontain'>
                        <label for='type'>Type</label><input type="text" name="type" value="" maxlength="200"
                                                             id="type"/>
                    </div>

                    <div class='fieldcontain required'>
                        <label for='author'>Author
                            <span class='required-indicator'>*</span>
                        </label>
                        <ul>
                            <g:each in="${persons}">
                                <li>${it.name}</li>
                            </g:each>
                        </ul>
                        <a href="/person/create?document.id=">Add Person</a>
                    </div>

                    <div class='fieldcontain'>
                        <label for='publishedDate'>Published Date</label><select name="publishedDate.id"
                                                                                 id="publishedDate">
                        <option value="null"></option>
                    </select>
                    </div>

                    <div class='fieldcontain'>
                        <label for='publishedLocation'>Published Location</label><input type="text"
                                                                                        name="publishedLocation"
                                                                                        value="" maxlength="200"
                                                                                        id="publishedLocation"/>
                    </div>

                    <div class='fieldcontain'>
                        <label for='changeLocation'>Change Location</label><ul></ul><a
                            href="/changeLocation/create?document.id=">Add ChangeLocation</a>
                    </div>

                    <div class='fieldcontain'>
                        <label for='shelfmark'>Shelfmark</label><input type="text" name="shelfmark" value=""
                                                                       maxlength="200" id="shelfmark"/>
                    </div>

                    <div class='fieldcontain'>
                        <label for='scale'>Scale</label><input type="text" name="scale" value="" maxlength="200"
                                                               id="scale"/>
                    </div>

                    <div class='fieldcontain'>
                        <label for='measurement'>Measurement</label><input type="text" name="measurement" value=""
                                                                           maxlength="200" id="measurement"/>
                    </div>

                    <div class='fieldcontain'>
                        <label for='volume'>Volume</label><input type="text" name="volume" value="" maxlength="200"
                                                                 id="volume"/>
                    </div>

                    <div class='fieldcontain'>
                        <label for='placeOfStorage'>Place Of Storage</label><input type="text" name="placeOfStorage"
                                                                                   value="" maxlength="200"
                                                                                   id="placeOfStorage"/>
                    </div>

                    <div class='fieldcontain'>
                        <label for='digitalCopy'>Digital Copy</label><input type="text" name="digitalCopy" value=""
                                                                            maxlength="200" id="digitalCopy"/>
                    </div>

                    <div class='fieldcontain'>
                        <label for='referencePerson'>Reference Person</label><ul></ul><a
                            href="/person/create?document.id=">Add Person</a>
                    </div>

                    <div class='fieldcontain'>
                        <label for='referenceBuilding'>Reference Building</label><ul></ul><a
                            href="/building/create?document.id=">Add Building</a>
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
