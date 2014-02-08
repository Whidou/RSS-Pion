/***************************************************************************//**
 * @file    AddFluxDialogFragment.java
 * @author  PERROCHAUD Clément
 * @author  TOMA Hadrien
 * @date    2014-02-02
 * @version 1.0
 *
 * Dialogue d'ajour d'un nouveau flux RSS.
 ******************************************************************************/

package com.rss_pion.dialogs;

/*** INCLUDES *****************************************************************/

import com.rss_pion.R;
import com.rss_pion.beans.Flux;
import com.rss_pion.configuration.Constants;
import com.rss_pion.database.dao.FluxDAO;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/*** MAIN CLASS ***************************************************************/

public class AddFluxDialogFragment extends DialogFragment {

    View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        
        this.view = inflater.inflate(R.layout.dialog_addflux, null);

        builder.setView(view)
               .setTitle(R.string.add_flux)
               .setPositiveButton(R.string.add, new boutonAjouter())
               .setNegativeButton(R.string.cancel,new boutonAnnuler());

        return builder.create();
    }

/*** LISTENERS ****************************************************************/
    
    private class boutonAjouter implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int id) {

            EditText champUrl;
            Flux flux;

            champUrl = (EditText) view.findViewById(R.id.url_flux);

            Log.e("AddFluxDialogFragment", "Création du flux.");

            flux = new Flux(champUrl.getText().toString());

            Log.e("AddFluxDialogFragment", "Ajout à la BDD.");

            FluxDAO.insertFluxIntoDB(flux);

            Log.e("AddFluxDialogFragment", "Ajout à la liste.");

            Constants.listOfFlux.add(flux);

            Log.e("AddFluxDialogFragment", Constants.listOfFlux.toString());

            Constants.adapterOfFlux.notifyDataSetChanged();
        }
    }
    
    private class boutonAnnuler implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int id) {
            Toast.makeText(
                    getActivity(),
                    R.string.canceled_add,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
