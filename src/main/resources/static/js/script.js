let modalMode = 'edit';

$( document ).ready(function() {
    $('#users-table .btn').click(function(event ) {
        prepareFormModal(event.currentTarget);
    });
});

function prepareFormModal(buttonElement) {
    fillFormModal(buttonElement);
    const btnMode = $(buttonElement).data('btn-type');
    if (modalMode != btnMode) {
        if (btnMode === "edit") {
            configureForEdit();
        } else {
            configureForDelete();
        }
        modalMode = btnMode;
    }
}

function fillFormModal(buttonElement) {
    const tableRow = $(buttonElement).closest('tr');

    const userId = tableRow.find('.user-id').text();
    const firstName = tableRow.find('.user-first-name').text();
    const lastName = tableRow.find('.user-last-name').text();
    const age = tableRow.find('.user-age').text();
    const email = tableRow.find('.user-email').text();
    const roles = tableRow.find('.user-role').attr('data-roles-id').split(' ');

    $('#idModal').val(userId);
    $('#firstNameModal').val(firstName);
    $('#lastNameModal').val(lastName);
    $('#ageModal').val(age);
    $('#emailModal').val(email);
    $('#roleModal option').prop('selected', false);
    roles.forEach(function(role) {
        $(`#roleModal option[value="${role}"]`).prop('selected', true);
    });
}

function configureForDelete() {
    const userForm = $('#userModalForm');
    const url = userForm.data('delete-url');
    userForm.attr('action', url);

    $('#userModalTitle').html('Delete user');

    $('#userModalForm input:not([name="_csrf"]), #userModalForm select').prop('readonly', true);
    $('#userModalForm input:not(#idModal, [name="_csrf"]), #userModalForm select').prop('disabled', true);

    $('#passwordModalGroup').prop('hidden', true);

    $('#saveChangesBtn')
        .removeClass('btn-primary')
        .addClass('btn-danger')
        .html('Delete')
}

function configureForEdit() {
    const userForm = $('#userModalForm');
    const url = userForm.data('edit-url');
    userForm.attr('action', url)

    $('#userModalTitle').html('Edit user');

    $('#userModalForm input:not(#idModal, [name="_csrf"]), #userModalForm select').prop('readonly', false);
    $('#userModalForm input:not(#idModal, [name="_csrf"]), #userModalForm select').prop('disabled', false);

    $('#passwordModalGroup').prop('hidden', false);

    $('#saveChangesBtn')
        .removeClass('btn-danger')
        .addClass('btn-primary')
        .html('Edit')
}