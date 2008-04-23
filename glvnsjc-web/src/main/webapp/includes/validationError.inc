<logic-el:messagesPresent>
    <span id="errorsHeader"><fmt:message key="errors.validation.header"/></span>
    <html-el:messages id="error">
      <li><c:out value="${error}"/></li>
    </html-el:messages>
    <hr>
</logic-el:messagesPresent>

