<!-- Modal -->
<div class="modal fade" id="myModal${status}" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Détail</h4>
            </div>
            <div class="modal-body">
                <g:render template="/event/display-event" model="[eventInstance: eventInstance]"></g:render>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>