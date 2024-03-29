package family.board.controller.ifs;

import family.board.model.network.Header;

public interface CrudInterface<Req,Res> {

    Header<Res> save(Header<Req> request);

    Header<Res> read(Long id);
    Header<Res> update(Header<Req> request);
    Header delete(Long id);
}
