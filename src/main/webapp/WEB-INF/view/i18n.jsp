<c:set var="language" scope="session" value="${empty sessionScope.language ? 'en_US' : sessionScope.language}" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.messages" var="bundle" scope="session"/>