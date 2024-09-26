import { useRecoilValue, useSetRecoilState } from 'recoil';
import { allSeriesList, episodesAtSeason, isHome, justOneSerie, newsSeriesList, popularSeriesList, seasonSelected } from './atom';
import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';


const defaultUrl = 'http://localhost:8080/series';

export function useGetNewsSeries(): () => void {
  return () => {
    const useSetNewsSeries = useSetRecoilState(newsSeriesList);

    async function getData() {
      try {
        const url = defaultUrl + '/lancamentos';
        const request = await fetch(url);
        const response = await request.json();
        useSetNewsSeries(response);
      } catch (e) {
        console.error('Erro ao fazer a requisição das novas séries.' + e);
      }
    }

    useEffect(() => {
      getData();
    }, []);
  };
}

export function useGetPopularSeries(): () => void {
  return () => {
    const useSetPopularSeries = useSetRecoilState(popularSeriesList);

    useEffect(() => {
      async function getData() {
        try {
          const url = defaultUrl + '/top5';
          const request = await fetch(url);
          const response = await request.json();
          useSetPopularSeries(response);
        } catch (e) {
          console.error('Erro ao fazer a requisição das séries populares.' + e);
        }
      }
      getData();
    }, []);
  };
}

export function useGetAllSeries(): () => void {
  return () => {
    const useSetAllSeries = useSetRecoilState(allSeriesList);

    useEffect(() => {
      async function getData() {
        try {
          const url = defaultUrl;
          const request = await fetch(url);
          const response = await request.json();
          useSetAllSeries(response);
        } catch (e) {
          console.error('Erro ao fazer a requisição das séries.' + e);
        }
      }

      getData();
    }, []);
  };
}

export function useGetJustOneSerie(): (id: string | undefined | number) => void {
  return (id: string | undefined | number) => {
    const setOneSerie = useSetRecoilState(justOneSerie);

    useEffect(() => {
      async function getData() {
        try {
          const url = defaultUrl + '/' + id;
          const request = await fetch(url);
          const response = await request.json();
          setOneSerie(response);
        } catch (e) {
          console.error('Erro ao fazer a requisição da série.' + e);
        }
      }

      getData();
    }, []);
  };
}

export function useGetEpisodesAtSeason(): (id: string | undefined | number) => void {
  return (id: string | undefined | number) => {
    const setEpisodesAtSeason = useSetRecoilState(episodesAtSeason);
    const useSetSeason = useSetRecoilState(seasonSelected);
    const actualSeason = useRecoilValue(seasonSelected);

    useEffect(() => {
      async function getData() {
        try {
          const url = defaultUrl + '/' + id + '/temporadas/' + actualSeason;
          const request = await fetch(url);
          const response = await request.json();
          setEpisodesAtSeason(response);
          useSetSeason(null);
        } catch (e) {
          console.error('Erro ao fazer a requisição dos episodios.' + e);
        }
      }

      if (actualSeason !== null) {
        getData();
      }
    }, [actualSeason]);
  };
}

export function useChangeHeader(): () => void {
  return () => {
    const location = useLocation();
    const setIsHomeValue = useSetRecoilState(isHome);

    if (location.pathname.includes('serie')) {
      setIsHomeValue(false);
      return;
    }

    setIsHomeValue(true);
  };
}