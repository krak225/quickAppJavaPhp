<?php
	require_once('fonctions/fonctions.php');
	require_once('classes/krakFormCreator.php');
	require_once('classes/krakFormCreatorJava.php');

if(isset($_POST['db']) and !empty($_POST['db'])){
	extract($_POST);
	
	$outpath=(isset($_POST['outpath']) and !empty($outpath))? $outpath.'/'.$db.'/' : $db.'/';
	
	$kfc = ($type_application == "java")? new krakFormCreatorJava() : new krakFormCreator();
	// die($type_application);
	
	// $kfc->setDB($db);
	$kfc->connectDB($db,$host,$user,$pass);
	$kfc->setPath($outpath);
	$kfc->setPackageName($package_name);
	
	//Copier le template
	copyFiles('install/theme-default/'.$type_application.'/', $outpath);
	
	
	//création des pages en fonctions des tables
	if($kfc->createAllForms()){
		
		$tab = explode('.', $package_name);
		
		$groupId = $tab[0].'.'.$tab[1];
		$artifactId = $tab[2];
		
		//envoyer le dossier compressé à l'internaute
		if($type_application == "java"){
			
			if(zipDir($artifactId.'.zip', $artifactId, 'zip/'))
			{
				print '<div class="succes">
				Votre application a été générée avec succès. 
				<a href="zip/'.$db.'.zip">Télécharger</a>
				</div>';
				//supprimer le dossier original et laisser le compressé
				supprimerDossier($db.'/');
				supprimerDossier($artifactId.'/');
			}
			else
			{
				print '<div class="succes">
				Votre application a été générée avec succès. 
				<a href="'.$artifactId.'/">Ouvrez le dossier ici</a>
				</div>';
			}
		
		}else{
			
			//envoyer le dossier compressé à l'internaute
			if(zipDir($db.'.zip', $db, 'zip/'))
			{
				print '<div class="succes">
				Votre application a été générée avec succès. 
				<a href="zip/'.$db.'.zip">Télécharger</a>
				</div>';
				//supprimer le dossier original et laisser le compressé
				supprimerDossier($db.'/');
			}
			else
			{
				print '<div class="succes">
				Votre application a été générée avec succès. 
				<a href="'.$db.'/">Ouvrez le dossier ici</a>
				</div>';
			}
			
		}
		
		
	}else{
		print '<div class="echec">Erreur lors de la génération de l\'application</div>';
	}

	// unset($_POST['db']);
	
}else{
	print '<div class="echec">Veillez entrer une base de données</div>';
}


?>