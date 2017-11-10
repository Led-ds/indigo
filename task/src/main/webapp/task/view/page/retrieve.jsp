<div id="shorten" ng-controller="shortenController">
	<form name="form" novalidate>
		<div class="row">
			<div class="col-sm-3 form-group" show-errors='{showSuccess: true}'>
			    <input id="shortenShortUrl" ng-model="shorten.shortUrl" name="shortenShortUrl" type="text" class="form-control" placeholder="Enter your Short URL">
			    <p class="help-block" ng-if="form.shortenShortUrl.$error.required">Short URL n&atilde;o definido!</p>
			</div>
		</div>			
	    
	  	<button type="button" class="btn btn-default" ng-click="searchShortUrl(shorten);">
	  	</button>
	  	
	</form>
</div>