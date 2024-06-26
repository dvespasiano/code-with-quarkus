package org.denis.feefoTest.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.denis.feefoTest.service.NormalizeService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/normalize")
@RequiredArgsConstructor
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class NormalizeResource {

    @Inject
    NormalizeService normalizeService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String normalizeJobTitle(@RequestBody String jobTitle) {
        return normalizeService.normalizingJobTitles(jobTitle);
    }
}
