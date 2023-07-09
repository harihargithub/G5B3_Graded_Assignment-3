function confirmDeleteTicket(ticketId) {
    var confirmationMessage = "Are you sure you want to delete this ticket with ID (" + ticketId + ")?";
    return confirm(confirmationMessage);
}
