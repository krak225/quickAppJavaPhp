<?php session_start();?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<title>krak QuickApp 2.0:: créateur d'application</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="keywords" content="krak,krakForm,Armand, Kouassi, Armand Kouassi"/>
	<meta name="description" content="krakForm est une application dévéloppé par Armand Kouassi" />
	<meta name="robots" content="noindex,nofollow">
	
	<link rel="stylesheet" id="wp-admin-css" href="css/wp-css/wp-admin.css" type="text/css" media="all">
	<link rel="stylesheet" id="buttons-css" href="css/wp-css/buttons.css" type="text/css" media="all">
	<link rel="stylesheet" id="colors-fresh-css" href="css/wp-css/colors-fresh.css" type="text/css" media="all">
	<link rel="stylesheet" id="colors-fresh-css" href="css/standard.css" type="text/css" media="all">
	
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
	<!--	
		$('document').ready(function(){
			$('#btn_valider').click(function(){
				$('#outbox').html('<div class="default-info">Traitement en cours...</div>');
				
				$.ajax({
					type: "POST",
					data: $('#kfc_form').serialize(),
					url: "kfc.php",
					success:function(k) {
						// $('#host').val('');
						// $('#user').val('');
						// $('#pass').val('');
						// $('#db').val('');
						// $('#package_name').val('');
						// $('#table').val('');
						$('#outbox').html(k);
						// setTimeout(function () { $('#outbox').html(''); }, 3000);
					},
					error:function(){
						$('#outbox').html('<div class="echec">Erreur lors de l\'envoi</div>');
						setTimeout(function () { $('#outbox').html(''); }, 3000);
					}
				});
			});		
		});
	-->
	</script>
</head>
<body>

	<div id="accessForm">
		<div class="login login-action-login wp-core-ui">
			<div id="login">
				<div style="text-align:center;font-size:20px;margin-bottom:10px;color:orangered;">QuickAppJava 3.0</div>
				<div class="info"><?php //print $info; ?></div>
				<form id="kfc_form" method="post" style="padding-top:0px;">
					<div class="info" id="outbox" style="height:50px;z-index:884;margin-top:;"></div>
					<table style="padding:0px;">
						<tr>
							<td style="width:50%;vertical-align:top;padding-right:5px;">
								<p>
									<label for="host">Adresse du serveur MySQL</label><br/>
									<input name="host" id="host" class="input" value="127.0.0.1" size="20" type="text"/>
								</p>
								<p>
									<label for="user">Utilisateur MySQL</label><br/>
									<input name="user" id="user" class="input" value="root" size="20" type="text"/>
								</p>
								<p>
									<label for="pass">Mot de passe</label><br/>
									<input name="pass" id="pass" class="input" value="" size="20" type="text"/>
								</p>
							</td>
							<td style="width:50%;vertical-align:top;padding-left:5px;">
								<p>
									<label for="db">Base de données</label><br/>
									<input name="db" id="db" class="input" size="20" type="text" value="grh_db"/>
								</p>
								<p>
									<label for="type_application">Type d'application</label><br/>
									<select name="type_application" class="input">
										<option value="java" selected>JAVA</option>
										<option value="php">PHP</option>
									</select>
								</p>
								<p>
									<label for="db">Package Name</label><br/>
									<input name="package_name" id="package_name" class="input" size="100" type="text" value="net.krak.quickapp"/>
								</p>
								<p class="submit">
									<input name="wp-submit" id="btn_valider" class="button button-primary button-large" value="Générer l'application" type="button"/>
								</p>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="clear"></div>
		</div>
	</div>

</body>
</html>