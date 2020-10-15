/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.harvest.cristin;

import org.dspace.content.Item;
import org.dspace.core.Context;
import org.dspace.harvest.HarvestingException;
import org.dspace.xmlworkflow.cristin.CristinException;
import org.dspace.xmlworkflow.cristin.MetadataManager;

import java.sql.SQLException;

/**
 * Implementation of the MetadataRemover interface, which allows us to
 * selectively clear old metadata before the item is updated
 */
public class DefaultMetadataRemover implements MetadataRemover
{
    /**
     * Selectively clear metadata before the item gets updated by the harvester
     *
     * This uses a pre-configured set of authority metadata fields, which are
     * controlled by the OAI-PMH harvester, leaving all other fields untouched
     *
     * @param context
     * @param item
     * @throws HarvestingException
     */
    public void clearMetadata(Context context, Item item) throws HarvestingException
    {
        MetadataManager mdm = new MetadataManager();
        try
        {
            mdm.removeAuthorityMetadata(context, item, "qdc", "metadata.authority");
        }
        catch (CristinException | SQLException e)
        {
            throw new HarvestingException(e);
        }
    }
}
