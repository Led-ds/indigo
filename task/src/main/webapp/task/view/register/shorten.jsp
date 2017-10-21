<div id="shorten" ng-controller="shortenController">
	<form name="form" novalidate>
		<div class="row">
			<div class="col-sm-3 form-group" show-errors='{showSuccess: true}'>
				<label for="shortenOriginalUrl">Url*</label>
			    <input type="text" class="form-control" ng-model="shorten.originalUrl" id="shortenOriginalUrl" name="shortenOriginalUrl" placeholder="URL" required>
			    <p class="help-block" ng-if="form.shortenOriginalUrl.$error.required">O campo URL &eacute; obrigat&oacute;rio!</p>
			</div>
		</div>			
		<div class="row">
			<div class="col-sm-3 form-group">
				<label for="shortencustomeAlias">Alias</label>
		   		<input type="text" class="form-control" id="shortencustomeAlias" name="shortencustomeAlias" ng-model="shorten.customeAlias" placeholder="ALIAS" required>
			</div>	
		</div>
	    
	  	<button type="button" class="btn btn-default" ng-click="saveOrUpdate(shorten);">
	  		{{($shorten!=null && $shorten.id>0)?'Atualizar':'Salvar'}}
	  	</button>
	

		<h3>List Register</h3>
			<table st-table="rowCollection" class="table table-striped">
				<tr>
					<th>Original URL</th>
					<th>Created</th>
					<th>Short URL</th>
					<th>CUSTOM ALIAS</th>
					<th></th>
					<th></th>
				</tr>
				<tr ng-repeat="row in shortens">
					<td>{{$row.url}}</td>
					<td>{{$row.created}}</td>
					<td>{{$row.shortUrl}}</td>
					<td>{{$row.customAlias}}</td>
					<td><a href="" ng-click="excluir(row.id);">excluir</a></td>
				</tr>
			</table>
		
	</form>
</div>