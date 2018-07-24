package edu.ctb.upm.midas.client_modules.filter.tvp.client.fallback;

/*@Component
public class HystrixClientFallbackFactory implements FallbackFactory<TvpClient> {
    @Override
    public TvpClient create(Throwable cause) {
        return new TvpClient(){
            @Override
            public Response getValidateSymptoms(Request request) {
                System.out.println("Fallback cause: " + cause.getMessage() + HttpStatus.OK);
                return new Response();
            }

            *//*@Override
            public ResponseEntity populateCloudant() {
                return new ResponseEntity("Fallback cause: " + cause.getMessage() + "\n", HttpStatus.OK);
            }*//*
        };
    }
}*/
