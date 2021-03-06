package fr.mediashare;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.skife.jdbi.v2.DBI;
import org.sqlite.SQLiteDataSource;

import fr.mediashare.ressources.CommentaireResource;
import fr.mediashare.ressources.ConnexionResource;
import fr.mediashare.ressources.InscriptionResource;
import fr.mediashare.ressources.ModifierProfilResource;
import fr.mediashare.ressources.MurGeneralRessource;
import fr.mediashare.ressources.RechercheResource;
import fr.mediashare.ressources.SuppressionUserResource;
import fr.mediashare.ressources.SupprimerCommentaireResource;
import fr.mediashare.ressources.SupprimerPostAdminResource;
import fr.mediashare.ressources.SupprimerPostResource;
import fr.mediashare.ressources.SupressionAdminResource;
import fr.mediashare.ressources.UserResource;
import fr.mediashare.utils.InitDB;


@ApplicationPath("/v1/")
public class App extends Application {
	public static SQLiteDataSource ds;
	
    @Override
    public Set<Class<?>> getClasses() {
		ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:"+System.getProperty("java.io.tmpdir")+System.getProperty("file.separator")+"media_bdd");
		System.out.println(ds.getUrl());
		
		dbi = new DBI(ds);
		InitDB.up(dbi);
    	
    	Set<Class<?>> s = new HashSet<Class<?>>();
    	s.add(InscriptionResource.class);
    	s.add(LoggingFilter.class);
    	s.add(MurGeneralRessource.class);
    	s.add(RechercheResource.class);
    	s.add(ConnexionResource.class);
    	s.add(SupressionAdminResource.class);
    	s.add(SuppressionUserResource.class);
    	s.add(ModifierProfilResource.class);
    	s.add(CommentaireResource.class);
    	s.add(SupprimerPostResource.class);
    	s.add(SupprimerCommentaireResource.class);
    	s.add(SupprimerPostAdminResource.class);
    	s.add(UserResource.class);
    	return s;
    }
    public static DBI dbi;
    
}
