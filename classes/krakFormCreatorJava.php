<?php
 class krakFormCreatorJava{
	public $pn;
	public $db;
	public $table;
	public $outpath;
	public $dbhost;
	public $dbh;
	public $dbu;
	public $dbp;
	
	public function __construct(){
		$this->auteur="{'name':'Armand Kouassi', 'email':'krak225@gmail.com', 'telephone':'+225 04 78 36 89'}";
		$this->pn='quickapp';//package name
		
		$this->outpath=$this->db.'/src/main/java/net/krak/quickapp';
		// $this->outpath=$this->db.'/';
		$this->dbhost='localhost';
		$this->dbu='root';
		$this->dbp='';
	}
 	public function connectDB($db,$h='localhost', $u='root',$p=''){
		//print $this->dbhost.'-'.$this->dbu;
		$this->db=$db;
		$this->dbhost=$h;
		$this->dbu=$u;
		$this->dbp=$p;
		
		$pdo = new PDO('mysql:host='.$this->dbhost.';dbname='.$db, $this->dbu, $this->dbp);
		$pdo->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_WARNING);
		
		$this->dbh = $pdo;
		
	}

 	public function setDB($db){
		// $this->db=$db;
		$this->connectDB();
	}

	public function setPath($outpath){
		$this->outpath=$outpath.'/src/main/java/net/krak/quickapp';
	}
	
	public function createAllForms(){
		$db=$this->db;$cpt=0;$err=0;
		
		
		$dbreq= 'SHOW TABLES';
		$stm = $this->dbh->query($dbreq);
        $tables = $stm->fetchAll(PDO::FETCH_COLUMN);
		
		// debug($tables);
		
		foreach($tables as $tablename){
			
			$outpath=$this->outpath;
			if($this->createFormulaire($tablename,$this->outpath)){
				$cpt++;
			}else{$err++;}

		}
		

		// print 'wcwc'.$cpt;
		if($err==0){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	private function createFiles($table,$base_url){
	
		if(!is_dir($base_url.'/'.'images/')){
			// mkdir($base_url.'/'.'images/');
		}
		
	}
	
	private function createFormulaire($table,$outpath){
		$this->createFiles($table,$outpath);
		
		$sql='DESCRIBE '.$table;
		$stm = $this->dbh->query($sql);
        $table_fields = $stm->fetchAll(PDO::FETCH_COLUMN);
		
		// debug($table_fields);
		
		
	////
	//le model
	$model="package net.krak.".$this->pn.".entities;\n\n";
	
	$model.="import javax.persistence.Entity\n";
	$model.="import javax.persistence.GeneratedValue\n";
	$model.="import javax.persistence.GenerationType\n";
	$model.="import javax.persistence.Id\n";
	$model.="import lombok.AllArgsConstructor\n";
	$model.="import lombok.Data\n";
	$model.="import lombok.NoArgsConstructor\n\n";
	
	$model.="@Entity\n";
	$model.="@Data\n";
	$model.="@NoArgsConstructor\n";
	$model.="@AllArgsConstructor\n";
	$model.="public class ".ucfirst($table)." {\n\n";
	
	$model.="\t@Id\n";
	$model.="\t@GeneratedValue(strategy=GenerationType.IDENTITY)\n";
	$model.="\tprivate Long id;\n";
	
	$i=0;
	foreach($table_fields as $field_name){

		if($i>0 and (strtolower($field_name)!=$table."_statut")){//PERMET DE NE PAS PRENDRE EN COMPTE L'ID DE LA TABLE
			//test s'il s'agit du clé parent qui a migré alors utilisation d'un select
			$tab=explode('_',$field_name);
			$x=sizeof($tab);
			//si c'est un table parent qui a migré
			if($tab[$x-1]=='id'){
				$table_parent=str_replace('_id',null,$field_name);
				$model.= "\t".'@OneToMany(mappedBy='.$table_parent.')'."\n";
				$model.= "\t".'private String '.$table_parent.'"'."\n";
				
			}else{//si pas clé parente
				$field_name=str_replace($table.'_',null,$field_name);
				// if($d->type=='int'){
					// $model.= "\t".'private int '.$field_name.';'."\n";
				// }else{
					$model.= "\t".'private String '.$field_name.';'."\n";
				// }
			}
			
		}
		$i++;
	}
	
	$model.='}'."\n";
	//fin de la page model
	
	
	//le repository
	$repository="package net.krak.".$this->pn.".repositories;\n\n";
	
	$repository.="import org.springframework.data.jpa.repository.JpaRepository;\n";
	$repository.="import net.krak.".$this->pn.".entities.".ucfirst($table).";\n\n";
	
	$repository.="public interface ".ucfirst($table)."Repository extends JpaRepository<".ucfirst($table).", Long> {\n\n";
	$repository.="}\n";
	//fin de la page repository
	
	
	
	//le service
	$service="package net.krak.".$this->pn.".services;\n\n";
	$service.="import java.util.List;\n";
	$service.="import java.util.Optional;\n\n";
	$service.="import net.krak.".$this->pn.".entities.".ucfirst($table).";\n\n";
	
	$service.="public interface ".ucfirst($table)."Service {\n\n";
	
	$service.="\tList<".ucfirst($table)."> get".ucfirst($table)."s();\n";
	$service.="\tOptional ".ucfirst($table)." get".ucfirst($table)."(Long id);\n";
	
	$service.="\t".ucfirst($table)." Add".ucfirst($table)."(".ucfirst($table)." ".strtolower($table).");\n";
	
	$service.="\t".ucfirst($table)." Edit".ucfirst($table)."(Long id, ".ucfirst($table)." ".strtolower($table).");\n";
	
	$service.="\tvoid ".ucfirst($table)." delete".ucfirst($table)."(Long id);\n\n";
	
	$service.="}\n";
	//fin de la page service
	
	
	//le serviceImpl
	$serviceImpl="package net.krak.".$this->pn.".services;\n\n";
	
	$serviceImpl.="import javax.transaction.Transactional;\n";
	$serviceImpl.="import org.springframework.stereotype.Service;\n\n";

	$serviceImpl.="import java.util.List;\n";
	$serviceImpl.="import java.util.Optional;\n\n";
	
	$serviceImpl.="import net.krak.".$this->pn.".entities.".ucfirst($table).";\n\n";
	

	$serviceImpl.="@Service\n";
	$serviceImpl.="@Transactional\n";
	$serviceImpl.="public class ".ucfirst($table)."ServiceImpl implements ".ucfirst($table)."Service {\n\n";
	
	$repositoryName = strtolower($table).'Repository';
	
	$serviceImpl.="private ".ucfirst($table)."Repository ".$repositoryName.";\n\n";
	
	$serviceImpl.="public ".ucfirst($table)."ServiceImpl(".ucfirst($table)."Repository ".$repositoryName.") {\n";
	$serviceImpl.="\tthis.".$repositoryName." = ".$repositoryName.";\n";
	$serviceImpl.="}\n\n";
	
	$serviceImpl.="@Override\n";
	$serviceImpl.='public List<'.ucfirst($table).'> get'.ucfirst($table)."s(){
	return $repositoryName.findAll();\n}\n\n";
	
	$serviceImpl.="@Override\n";
	$serviceImpl.='public '.ucfirst($table).' get'.ucfirst($table)."(Long id){
	return $repositoryName.findById(id);\n}\n\n";
	
	$serviceImpl.="@Override\n";
	$serviceImpl.='public '.ucfirst($table).' Add'.ucfirst($table).'('.ucfirst($table).' '.strtolower($table)."){
	return $repositoryName.save(".strtolower($table).");\n}\n\n";
	
	$serviceImpl.="@Override\n";
	$serviceImpl.='public '.ucfirst($table).' Edit'.ucfirst($table).'(Long id, '.ucfirst($table).' '.strtolower($table)."){
	return $repositoryName.save(".strtolower($table).");\n}\n\n";
	
	$serviceImpl.="@Override\n";
	$serviceImpl.='public '.ucfirst($table).' delete'.ucfirst($table)."(Long id){
	return $repositoryName.delete(id);\n}\n\n";
	
	$serviceImpl.='}'."\n";
	//fin de la page serviceImpl
	
	
	
	//le controller
	$controller="package net.krak.".$this->pn.".controllers;\n\n";
	$controller.="import java.security.Principal;\n";
	$controller.="import java.util.List;\n";
	$controller.="import java.util.Optional;\n\n";

	$controller.="import org.springframework.security.access.prepost.PostAuthorize;\n";
	$controller.="import org.springframework.web.bind.annotation.GetMapping;\n";
	$controller.="import org.springframework.web.bind.annotation.PathVariable;\n";
	$controller.="import org.springframework.web.bind.annotation.PostMapping;\n";
	$controller.="import org.springframework.web.bind.annotation.RequestBody;\n";
	$controller.="import org.springframework.web.bind.annotation.RequestMapping;\n";
	$controller.="import org.springframework.web.bind.annotation.RestController;\n\n";

	$controller.="import net.krak.".$this->pn.".entities.".ucfirst($table).";\n";
	$controller.="import net.krak.".$this->pn.".services.".ucfirst($table)."Service;\n\n";
	
	$controller.="@RestController\n";
	$controller.="@RequestMapping(path=\"/api/\")\n";
	$controller.="public class ".ucfirst($table)."Controller {\n\n";
	
	$serviceName = strtolower($table).'Service';
	
	//$controller.="@Autowired;\n";
	$controller.="public ".ucfirst($table)."Service ".$serviceName.";\n\n";
	
	$controller.="public ".ucfirst($table)."Controller(".ucfirst($table)."Service ".$serviceName.") {\n";
	$controller.="\tthis.".$serviceName." = ".$serviceName.";\n";
	$controller.="}\n\n";
	
	$controller.='@GetMapping(path="/'.strtolower($table).'s")'."\n";
	$controller.='public List<'.ucfirst($table).'> get'.ucfirst($table)."s(){
	return $serviceName.findAll();\n}\n\n";
	
	$controller.='@GetMapping(path="/'.strtolower($table).'/{id}")'."\n";
	$controller.='public '.ucfirst($table).' get'.ucfirst($table)."(@PathVariable Long id){
	return $serviceName.findById(id);\n}\n\n";
	
	$controller.='@PostMapping(path="/'.strtolower($table).'")'."\n";
	$controller.='public '.ucfirst($table).' Add'.ucfirst($table).'('.ucfirst($table).' '.strtolower($table)."){
	return $serviceName.save(".strtolower($table).");\n}\n\n";
	
	$controller.='@PutMapping(path="/'.strtolower($table).'/{id}")'."\n";
	$controller.='public '.ucfirst($table).' Edit'.ucfirst($table).'(@PathVariable Long id, '.ucfirst($table).' '.strtolower($table)."){
	return $serviceName.save(".strtolower($table).");\n}\n\n";
	
	$controller.='@DeleteMapping(path="/'.strtolower($table).'/{id}")'."\n";
	$controller.='public '.ucfirst($table).' Delete'.ucfirst($table)."(@PathVariable Long id){
	return $serviceName.delete(id);\n}\n\n";
	
	$controller.='}'."\n";
	//fin de la page controller
	
	
	/*
	$i=0;
	foreach($table_fields as $field_name){

		if($i>0 and (strtolower($field_name)!=$table."_statut")){//PERMET DE NE PAS PRENDRE EN COMPTE L'ID DE LA TABLE
			//test s'il s'agit du clé parent qui a migré alors utilisation d'un select
			$tab=explode('_',$field_name);
			$x=sizeof($tab);
			//si c'est un table parent qui a migré
			if($tab[$x-1]=='id'){
				$table_parent=str_replace('_id',null,$field_name);
				$controller.= "\t".'@OneToMany(mappedBy='.$table_parent.')'."\n";
				$controller.= "\t".'private String '.$table_parent.'"'."\n";
				
			}else{//si pas clé parente
				$field_name=str_replace($table.'_',null,$field_name);
				// if($d->type=='int'){
					// $form.= "\t".'private int '.$field_name.';'."\n";
				// }else{
					$controller.= "\t".'private String '.$field_name.';'."\n";
				// }
			}
			
		}
		$i++;
	}
	*/
	

		//création des fichers
	
		$base_url=$outpath;//die($outpath);
		$file_model=$base_url.'/entities/'.ucfirst($table).'.java';
		$file_repository=$base_url.'/repositories/'.ucfirst($table).'Repository.java';
		$file_service=$base_url.'/services/'.ucfirst($table).'Service.java';
		$file_serviceImpl=$base_url.'/services/'.ucfirst($table).'ServiceImpl.java';
		$file_controller=$base_url.'/controllers/'.ucfirst($table).'Controller.java';
		
		$fp_m=fopen($file_model,'w+');
		$fp_r=fopen($file_repository,'w+');
		$fp_s=fopen($file_service,'w+');
		$fp_si=fopen($file_serviceImpl,'w+');
		$fp_c=fopen($file_controller,'w+');
		
		if(fputs($fp_m, $model) && fputs($fp_r, $repository) && fputs($fp_s, $service) && fputs($fp_si, $serviceImpl) && fputs($fp_c, $controller)){
			
			fclose($fp_m);
			fclose($fp_r);
			fclose($fp_s);
			fclose($fp_si);
			fclose($fp_c);
			
			return true;
			
		}else{
			
			fclose($fp_m);
			fclose($fp_r);
			fclose($fp_s);
			fclose($fp_si);
			fclose($fp_c);
			
			return false;
			
		}
		
		
		
	}
	
	
	function haveImage($table){
		
		
		
		/*
		$tab=array();
		$req=mysql_query('select * from '.$table);
		while($d=mysql_fetch_field($req)){
			$lib=strtolower(str_replace($table.'_',null,$d->name));
			$tab[]=$lib;
		}
		if(in_array('image',$tab)){
			return true;
		}elseif(in_array('logo',$tab)){
			return true;
		}elseif(in_array('photo',$tab)){
			return true;
		}elseif(in_array('picture',$tab)){
			return true;
		}else{
			return false;
		}
		*/
		
	}
	
	function getImageField($table){
		$tab=array();
		$req=mysql_query('select * from '.$table);
		while($d=mysql_fetch_field($req)){
			$lib=strtolower(str_replace($table.'_',null,$d->name));
			$tab[]=$lib;
		}
		if(in_array('image',$tab)){
			return 'image';
		}elseif(in_array('logo',$tab)){
			return 'logo';
		}elseif(in_array('photo',$tab)){
			return 'photo';
		}elseif(in_array('picture',$tab)){
			return 'picture';
		}else{
			return '';
		}
	}

 
	function copyFiles($dir2copy,$dir_paste){
	  // On vérifie si $dir2copy est un dossier
	  if (is_dir($dir2copy)) {
	 
		// Si oui, on l'ouvre
		if ($dh = opendir($dir2copy)) {     
		  // On liste les dossiers et fichiers de $dir2copy
		  while (($file = readdir($dh)) !== false) {
			// Si le dossier dans lequel on veut coller n'existe pas, on le créé
			if (!is_dir($dir_paste)) mkdir ($dir_paste, 0777);
	 
			  // S'il s'agit d'un dossier, on relance la fonction rÃ©cursive
			  if(is_dir($dir2copy.$file) && $file != '..'  && $file != '.') copyFiles( $dir2copy.$file.'/' , $dir_paste.$file.'/' );     
				// S'il sagit d'un fichier, on le copie simplement
				elseif($file != '..'  && $file != '.') copy ( $dir2copy.$file , $dir_paste.$file );                                       
			 }
	 
		  // On ferme $dir2copy
		  closedir($dh);
			return true;
		}
	 
	  }
	}

	public function creerFichier($filename,$content){
		$fp=fopen($filename,'a+');
		fputs($fp,$content);
		fclose($fp);
	}
	
 }
 
?>